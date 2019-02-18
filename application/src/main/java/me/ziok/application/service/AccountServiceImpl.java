package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.exceptions.ResourceNotFoundException;
import me.ziok.application.model.Account;
import me.ziok.application.model.AuthProviderType;
import me.ziok.application.util.MailSendUtil;

import me.ziok.application.model.Post;
import me.ziok.application.model.ProfileDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PostService postService;

    @Override
    public Account loadAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account loadAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElse(null);
    }


    @Override
    public Account saveAccount(Account account) {

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return accountRepository.save(account);

    }

    @Override
    public Account deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            return  null;
        }

        accountRepository.delete(account);
        return account;
    }

    @Override
    public Account updateAccount(Long accountId,Account accountDetails) {

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));
        if (accountDetails.getEmail() != null) {
            account.setEmail(accountDetails.getEmail());
        }

        if (accountDetails.getPassword() != null) {
            account.setPassword(passwordEncoder.encode(accountDetails.getPassword()));
        }

        if (accountDetails.getNickName() != null) {
            account.setNickName(accountDetails.getNickName());
        }

        if (accountDetails.getProviderType() != null) {
            account.setProviderType(accountDetails.getProviderType());
        }

        if (accountDetails.getProviderId() != null) {
            account.setProviderId(accountDetails.getProviderId());
        }

        return accountRepository.save(account);
    }

    @Override
    public boolean isAbleToRegister(String email, String nickName) {

        //todo: 이메일 글자수가 적절한지, 닉네임 글자수가 적절한지, 패스워드 글자수나 정책에 적절한지 등 확인

        if (accountRepository.existsByEmailOrNickName(email, nickName)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !accountRepository.existsByEmail(email);
    }


    public boolean confirmUser(String email) {
        Account account = accountRepository.findByEmail(email).get();

        if (account == null) {
            return false;
        } else {
            String authenticationCode = getRandomNumber();
            account.setAuthenticationCode(authenticationCode);
            accountRepository.save(account);
            new MailSendUtil().sendMail(email, authenticationCode);
            return true;
        }
    }

    public boolean matchAuthenticationCode(String email, String code){
        Account account = accountRepository.findByEmail(email).get();

        if(account == null || !account.getAuthenticationCode().equals(code))
            return false;
        else{
            return true;
        }
    }

    public boolean changeUserPassword(String email, String password){
        Account account = accountRepository.findByEmail(email).get();
        if(account == null)
            return false;
        else{
            account.setPassword(passwordEncoder.encode(password));
            account.setAuthenticationCode(null);
            accountRepository.save(account);
            return true;
        }
    }

    //비밀번호 변경 시 인증을 위해 6자리 랜덤 넘버를 생성해서 리턴해주는 메서드
    private String getRandomNumber(){
        double mathRandom = Math.random();
        int value = (int)(mathRandom*1000000);
        return String.valueOf(value);
    }

    @Override
    public ProfileDTO constructProfile(Long accountId) {

        //todo: builder 패턴으로 수정
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setInCompleteNum(postService.loadOpenPostsWithAccountId(accountId).size());
        profileDTO.setCompleteNum(postService.loadClosedPostsWithAccountId(accountId).size());
        profileDTO.setIncompletePosts(postService.loadOpenPostsWithAccountId(accountId));
        profileDTO.setCompletePosts(postService.loadClosedPostsWithAccountId(accountId));

        List<Post> postsWithComments = postService.findPostsWithCommentsByAccountId(accountId);

        profileDTO.setCommentNum(postsWithComments.size());
        profileDTO.setPostsWithComment(postsWithComments);



        return profileDTO;
    }
}
