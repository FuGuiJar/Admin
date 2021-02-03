package top.fuguijar.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.DigestUtils;
import top.fuguijar.config.SpringConfig;
import top.fuguijar.pojo.User;
import top.fuguijar.utils.AesUtil;
import top.fuguijar.utils.RedisUtil;

import javax.annotation.Resource;

import java.util.Arrays;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
@SpringJUnitConfig(classes = SpringConfig.class)
class UserServiceTest {
    @Autowired
    private Environment environment;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;


    @Test
    void findByUsername() {
        System.out.println(userService.findByUsername("Jack"));
    }


    /**
    *
    */
    @Test
    public void a6() throws Exception {
        String admin = AesUtil.aesEncryption("admin");
        System.out.println(admin);
        String s = AesUtil.aesDecrypt(admin);
        System.out.println(s);
    }


    @Test
    public void a2(){
        rolePermissionService.findById(1).forEach(System.err::println);
    }
    @Test
    public void a3(){
        userRoleService.findById(1).forEach(System.err::println);
    }

    @Test
    public void a4(){
        userRoleService.findById(2).forEach( userRole -> {
            rolePermissionService.findById(userRole.getRoleId()).forEach(rolePermission -> {
                System.err.println(rolePermission);
            });
        });
    }

    /**
    *
    */
    @Test
    public void a1(){
//        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        yaml.setResources(new ClassPathResource("application.yml"));
//        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
//        Properties object = yaml.getObject();
//        object.forEach((k,v) -> System.out.println(k +"--"+ v));
        String app = environment.getProperty("app");
        System.out.println(app);
    }


    /**
    *
    */
    @Test
    public void a5(){
        String s = Arrays.toString(DigestUtils.md5Digest("fa".getBytes()));
//        System.out.println(s);
        //生成从ASCII 32到126组成的随机字符串 （包括符号）
        String salt = RandomStringUtils.randomAscii(12);
        System.out.println(salt);

//        String admin = new Md5Hash("admin").toString();
//        MD5Util md5Helper = new MD5Util();


//        String admin = md5Helper.getMD5ofStr("admin");
//        System.out.println(admin);

//        String encode = md5Helper.getTwiceMD5ofString(md5Helper.getTwiceMD5ofString(admin));

//        String encode = MD5Encoder.encode(admin.getBytes());
//        System.out.println(encode);

    }

    /**
    *
    */
    @Test
    public void b(){
        userService.list().forEach(System.err::println);
    }

    /**
    *
    */
    @Test
    public void a(){
        String hashAlgorithmName = "md5";
        String credentials = "123";//密码
        int hashIterations = 2;
        ByteSource credentialsSalt = ByteSource.Util.bytes("Rose");//账号
        String  obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations).toHex();
        System.out.println(obj);
    }
    //c56e34c088cd583890ed36774a83d771

    @Test
    public void getuser(){
        Object o = redisUtil.get("aaa");
        System.err.println(o);
    }

    /**
    *
    */
    @Test
    public void set(){
//        boolean aaa = redisUtil.set("aaa", "213");
        User user = new User();
        user.setUsername("张三");
        user.setUserId(2);
        user.setPassword("123");
        boolean user1 = redisUtil.set("user", user);
        System.err.println(user1);
        User user2 = (User) redisUtil.get("user");
        System.err.println(user2);
    }





}