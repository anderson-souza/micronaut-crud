package com.aps.config.security;

import com.aps.model.UserEntity;
import com.aps.repository.UserRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.apache.commons.codec.digest.DigestUtils;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final UserRepository userRepository;

    public AuthenticationProviderUserPassword(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flowable.create(emitter -> {
            final String identity = (String) authenticationRequest.getIdentity();

            final UserEntity user = userRepository.findByEmail(identity)
                    .orElseThrow(() -> new AuthenticationException(new AuthenticationFailed("Usuário não cadastrado")));

            String secret = DigestUtils.md5Hex((String) authenticationRequest.getSecret());
            if (user.getPassword().equals(secret)) {
                emitter.onNext(new UserDetails(identity, new ArrayList<>()));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed("Senha incorreta")));
            }

        }, BackpressureStrategy.ERROR);
    }
}
