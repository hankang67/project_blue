package com.sparta.projectblue.domain.auth.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sparta.projectblue.config.JwtUtil;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.common.exception.AuthException;
import com.sparta.projectblue.domain.user.entity.User;
import com.sparta.projectblue.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "kakao 서비스 로직")
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Value("${KAKAO_CLIENT_ID}")
    private String client_id;

    @Value("${KAKAO_REDIRECT_URI}")
    private String redirect_uri;

    public String kakaoLogin() {

        return UriComponentsBuilder.fromUriString("https://kauth.kakao.com")
                .path("/oauth/authorize")
                .queryParam("client_id", client_id)
                .queryParam("redirect_uri", redirect_uri)
                .queryParam("response_type", "code")
                .build()
                .toString();
    }

    public String callback(String code) throws Exception {

        if (code == null) {
            throw new AuthException("인증코드를 받지 못했습니다.");
        }

        String accessToken;

        try {
            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", client_id);
            params.add("redirect_uri", redirect_uri);
            params.add("code", code);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity =
                    new HttpEntity<>(params, headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            "https://kauth.kakao.com/oauth/token",
                            HttpMethod.POST,
                            httpEntity,
                            String.class);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());

            accessToken = (String) jsonObject.get("access_token");
        } catch (Exception e) {
            throw new AuthException("Kakao 요청 실패");
        }

        return getUserInfo(accessToken);
    }

    private String getUserInfo(String accessToken) throws Exception {

        // HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader 담기
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "https://kapi.kakao.com/v2/user/me",
                        HttpMethod.POST,
                        httpEntity,
                        String.class);

        // Response 데이터 파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
        JSONObject account = (JSONObject) jsonObject.get("kakao_account");
        JSONObject profile = (JSONObject) account.get("profile");

        Long kakaoId = (Long) jsonObject.get("id");
        String email = String.valueOf(account.get("email"));
        String nickname = String.valueOf(profile.get("nickname"));

        User kakaoUser =
                userRepository
                        .findByEmail(email)
                        .orElseGet(
                                () ->
                                        new User(
                                                email,
                                                nickname,
                                                "kakaoUser",
                                                UserRole.ROLE_USER,
                                                kakaoId));

        if (kakaoUser.getKakaoId() == null) {
            kakaoUser.InsertKakaoId(kakaoId);
        }

        User savedUser = userRepository.save(kakaoUser);

        return jwtUtil.createToken(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getUserRole());
    }
}
