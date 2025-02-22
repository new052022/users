package monaco.bot.users.mapper;

import monaco.bot.users.dto.UserInfoRequestDto;
import monaco.bot.users.dto.UserInfoResponseDto;
import monaco.bot.users.model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "exchanges", ignore = true)
    UserInfo toUserInfo(UserInfoRequestDto request);

    UserInfoResponseDto toUserInfoResponseDto(UserInfo savedUser);

}
