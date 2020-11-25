import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * @author 余俊锋
 * @date 2020/11/25 20:02
 * @Description
 */
public class demo {
    @Test
    //签证
        public void test1(){

            JwtBuilder builder = Jwts.builder().setId("11")
                    .setIssuer("me")              //签发者
                    .setAudience("you")           //接收方
                    .setSubject("小准")            //用户名
                    .setIssuedAt(new Date())      //签发时间
//                .setNotBefore(new Date())      //该签发token,在指定时间之前是不允许使用的
                    .signWith(SignatureAlgorithm.HS256, "dfbz")       //设置秘钥,并指定头部算法
                    .setExpiration(new Date(new Date().getTime()+300*1000))      //过期时间(300秒后过期)
                    .claim("role","admin")     //自定义用户信息
                    .claim("mobile","110");     //自定义用户信息

            System.out.println(builder.compact());
        }
        //解析
        @Test
        public void test2(){

            String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMSIsImlzcyI6Im1lIiwiYXVkIjoieW91Iiwic3ViIjoi5bCP5YeGIiwiaWF0IjoxNjA2MzA2MzA4LCJleHAiOjE2MDYzMDY2MDgsInJvbGUiOiJhZG1pbiIsIm1vYmlsZSI6IjExMCJ9.ASpyMOoldZsudAwiOK1Zoh8k4DayZ_nrJVEa4_JuXuk";

            //解析token字符串
            Claims claims = Jwts.parser().
                    setSigningKey("dfbz").      //当初签发时的秘钥
                    parseClaimsJws(token).      //token字符串
                    getBody();

            System.out.println("id: "+claims.getId());
            System.out.println("Issuer: "+claims.getIssuer());          //签发者
            System.out.println("Audience: "+claims.getAudience());      //接收方
            System.out.println("Subject: "+claims.getSubject());        //用户名
            System.out.println("IssuedAt: "+claims.getIssuedAt());      //签发时间
            System.out.println("Expiration: "+claims.getExpiration());  //查看过期时间
            System.out.println("role: "+claims.get("role"));            //查看自定义信息role
            System.out.println("mobile: "+claims.get("mobile"));        //查看自定义信息mobile


        }


}
