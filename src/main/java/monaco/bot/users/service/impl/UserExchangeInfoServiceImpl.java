package monaco.bot.users.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import monaco.bot.users.dto.NewUserExchangeRequestDto;
import monaco.bot.users.dto.UserExchangeResponseDto;
import monaco.bot.users.mapper.UserExchangeInfoMapper;
import monaco.bot.users.model.UserExchangeInfo;
import monaco.bot.users.repository.UserExchangeInfoRepository;
import monaco.bot.users.service.interfaces.UserExchangeInfoService;
import monaco.bot.users.service.interfaces.UserInfoService;
import monaco.bot.users.util.EncryptDecryptGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserExchangeInfoServiceImpl implements UserExchangeInfoService {

    private final UserExchangeInfoRepository exchangeInfoRepository;

    private final EncryptDecryptGenerator encryptDecryptGenerator;

    private final UserInfoService userInfoService;

    private final UserExchangeInfoMapper userExchangeInfoMapper;

    @Override
    public UserExchangeResponseDto save(NewUserExchangeRequestDto request) {
        UserExchangeInfo exchangeInfo = userExchangeInfoMapper.toExchangeInfo(request);
        this.encodeExchangeData(exchangeInfo);
        UserExchangeInfo savedExchangeInfo = exchangeInfoRepository.save(exchangeInfo);
        return userExchangeInfoMapper.toExchangeInfoResponseDto(savedExchangeInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserExchangeResponseDto> getExchanges(Long userId) {
        List<UserExchangeInfo> exchangeInfos = exchangeInfoRepository.findByUserInfoId(userId);
        return userExchangeInfoMapper.toExchangeInfoResponseList(exchangeInfos);
    }

    @Override
    public UserExchangeInfo getExchangeInfoByNameAndUserId(Long userId, String exchange) {
        return userInfoService.getUserById(userId).getExchanges().stream()
                .filter(userExchange -> userExchange.getExchange().getName().equalsIgnoreCase(exchange))
                .findFirst().orElseThrow(() -> new NoSuchElementException(
                        String.format("User with id №%d doesn't have info about exchange - %s", userId, exchange)));
    }

    @Override
    public List<UserExchangeInfo> findById(long userId) {
        return exchangeInfoRepository.findByUserInfoId(userId);
    }

    @SneakyThrows
    private void encodeExchangeData(UserExchangeInfo exchangeInfo) {
        exchangeInfo.setApiKey(encryptDecryptGenerator.encryptData(exchangeInfo.getApiKey()));
        exchangeInfo.setSecretKey(encryptDecryptGenerator.encryptData(exchangeInfo.getSecretKey()));
    }

}
