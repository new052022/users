package monaco.bot.users.service.impl;

import lombok.RequiredArgsConstructor;
import monaco.bot.users.dto.UserInfoRequestDto;
import monaco.bot.users.dto.UserInfoResponseDto;
import monaco.bot.users.mapper.UserInfoMapper;
import monaco.bot.users.model.UserInfo;
import monaco.bot.users.repository.UserInfoRepository;
import monaco.bot.users.service.interfaces.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfoResponseDto save(UserInfoRequestDto userInfo) {
        UserInfo newUser = userInfoMapper.toUserInfo(userInfo);
        UserInfo savedUserInfo = userInfoRepository.save(newUser);
      return userInfoMapper.toUserInfoResponseDto(savedUserInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoResponseDto getUser(Long id) {
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("User with id № %d doesn't exist", id)));
        return userInfoMapper.toUserInfoResponseDto(userInfo);
    }

    @Override
    public UserInfo getUserById(Long userId) {
        return userInfoRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(
                String.format("User with id %d doesn't exist", userId)));
    }

}
