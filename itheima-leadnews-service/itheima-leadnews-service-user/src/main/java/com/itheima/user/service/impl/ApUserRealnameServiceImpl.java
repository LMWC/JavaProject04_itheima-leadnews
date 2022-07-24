package com.itheima.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.article.feign.ApAuthorFeign;
import com.itheima.article.pojo.ApAuthor;
import com.itheima.common.constants.BusinessConstants;
import com.itheima.common.pojo.Result;
import com.itheima.media.feign.WmUserFeign;
import com.itheima.media.pojo.WmUser;
import com.itheima.user.mapper.ApUserMapper;
import com.itheima.user.mapper.ApUserRealnameMapper;
import com.itheima.user.pojo.ApUser;
import com.itheima.user.pojo.ApUserRealname;
import com.itheima.user.service.ApUserRealnameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * APP实名认证信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {
    @Autowired
    private ApUserRealnameMapper realnameMapper;

    @Autowired
    private ApUserMapper apUserMapper;


    @Autowired
    private WmUserFeign wmUserFeign;

    @Autowired
    private ApAuthorFeign apAuthorFeign;

    //1.sql语句合并为一个
    //2.远程调用 4次 可以优化成2次

    @Override
    public void pass(Integer id) {
        //审核通过   update 表 set status=9 where id=?


        //1.先通过审核
        ApUserRealname record = new ApUserRealname();
        record.setId(id);
        record.setStatus(BusinessConstants.ApUserRealnameConstants.SHENHE_SUCCESS);
        realnameMapper.updateById(record);


        //2.再通过远程调用创建自媒体账号

        //构建数据  拿出ap_user表的数据 将其 copy到这个对象中即可 补全其他的属性的值
        //2.1 根据id 获取到实名认证的信息表记录  再获取到表记录中的user_id的值 （app_user的主键）
        Integer userId = realnameMapper.selectById(id).getUserId();
        //2.2 根据user_id 从ap_user表中查询出表的记录
        ApUser apUser = apUserMapper.selectById(userId);


        WmUser wmUser = wmUserFeign.getByApUserId(userId);

        if (wmUser == null) {
            wmUser = new WmUser();
            //2.3 copy
            BeanUtils.copyProperties(apUser, wmUser);
            wmUser.setId(null);
            wmUser.setApUserId(userId);
            wmUser.setStatus(9);
            wmUser.setType(0);
            wmUser.setCreatedTime(LocalDateTime.now());

            //判断如果有这个账号了 查询如果有 则不创建 如果没有则创建账号

            Result<WmUser> result = wmUserFeign.save(wmUser);
            //创建自媒体之后 将自增的ID 返回出来
            wmUser = result.getData();
        }

        //3.再通过远程调用创建作者的账号

        ApAuthor apAuthor = apAuthorFeign.getByApUserId(userId);

        if (apAuthor == null) {
            apAuthor = new ApAuthor();
            apAuthor.setName(apUser.getName());
            apAuthor.setType(2);
            apAuthor.setUserId(userId);//ap_user的ID
            apAuthor.setCreatedTime(LocalDateTime.now());
            apAuthor.setWmUserId(wmUser.getId());
            apAuthorFeign.save(apAuthor);
        }
    }

    @Override
    public void reject(Integer id, String reason) {
        //update 表 set status=2,reason=? where id=?
        //驳回
        ApUserRealname record = new ApUserRealname();
        record.setId(id);
        record.setReason(reason);
        record.setStatus(BusinessConstants.ApUserRealnameConstants.SHENHE_FAIL);
        realnameMapper.updateById(record);
    }
}
