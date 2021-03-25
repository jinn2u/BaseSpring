package com.cos.blog.test;

import com.cos.blog.Repository.UserRepository;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired  // 의존성 주입(DI)
    private UserRepository userRepository;

//    @GetMapping("/dummy/user/{id}")
//    public User detail(@PathVariable int id){
//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해댱 유저는 존재하지 않습니다.id: "+id);
//            }
//        });
//        return user;
//    }
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

     @GetMapping("/dummy/user/page")
     public List<User> pageList(@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUsers = userRepository.findAll(pageable);
        List<User> users = pagingUsers.getContent();
        return users;
     }

     //formTag만 받을 수 있다.
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getPassword());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return"회원가입이 완료되었습니다.";
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setEmail(requestUser.getEmail());
        user.setPassword(requestUser.getPassword());
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        }catch (IllegalArgumentException e){
            return "삭제에 실패하였습니다. 해당하는 아이디는 존재하지 않습니다.";
        }
        return "삭제되었습니다. id: "+id;
    }
}
