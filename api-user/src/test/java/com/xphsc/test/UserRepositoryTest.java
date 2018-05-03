package com.xphsc.test;


import com.baidu.unbiz.easymapper.MapperFactory;
import com.github.xphsc.bean.register.RegisterBean;
import com.github.xphsc.collect.Lists;
import com.github.xphsc.json.JSONHelper;
import com.github.xphsc.util.ClassUtil;
import com.github.xphsc.util.ObjectUtil;
import com.github.xphsc.util.StringUtil;
import com.xphsc.api.frame.common.util.BeanByMapper;
import com.xphsc.api.user.UserApplication;
import com.xphsc.api.user.model.User;
import com.xphsc.api.user.repository.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = UserApplication.class)
public class UserRepositoryTest {

  //  @Autowired
    UserDao userRepository;

    @Test
    public void findALL(){
      /* List<User> userList= userRepository.findAll();
        for(User user:userList){
            System.out.println("111"+user.getUname()  );
        }

        System.out.println("111" + StringUtil.substringAfterLast("asb_y", "_")  );
*/
        User user =new User();
        user.setId(1);
       // System.out.println("111" + JSONHelper.toJSON(get(User.class, getNullPropertyNames(user))));
       // ;List ignoreList = getNullPropertyNames(user) != null? Arrays.asList(getNullPropertyNames(user)):null;
       BeanUtils.copyProperties(user, User.class, getNullPropertyNames(user));
        System.out.println("111" + JSONHelper.toJSON( user));

   /*public static void copyPropertiesIgnoreNull(Object src, Object target){
            BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
        }*/

    }
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        System.out.println("222" + JSONHelper.toJSON( pds));
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            System.out.println("333" + JSONHelper.toJSON( srcValue));
            if (srcValue== null) emptyNames.add(pd.getName());
        }
        System.out.println("444" + JSONHelper.toJSON( emptyNames));
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    public static <T> T   get(Class<T> target, String... ignoreProperties) {
        T actualEditable = (T) target.getClass();
       // T actualEditable = (T) clazz;
       /* try {

        //    actualEditable = target.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
*/
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable.getClass());
        List ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;
        for (int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                    try {
                     //   Object ex = writeMethod.invoke(target, new Object[0]);
                        actualEditable= (T) writeMethod.invoke(target, new Object[]{targetPd});
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return actualEditable;
    }


   /* public static <T> T get1(Class<T> targetClass, String... ignoreProperties){
        try {
            T target = targetClass.newInstance();
            *//** 获取源对象的所有变量 *//*
            Field[] fields = orig.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (RegisterBean.isStatic(field)) {
                    continue;
                }
                *//** 获取目标方法 *//*
                Field targetField = RegisterBean.getTargetField(targetClass, field.getName());
                if (targetField == null) {
                    continue;
                }
                Object value = RegisterBean.getFiledValue(field, orig);
                if (value == null) {
                    continue;
                }
                Class type1 = field.getType();
                Class type2 = targetField.getType();
                //两个类型是否相同
                boolean sameType = type1.equals(type2);
                if (RegisterBean.isBasicType(type1)) {
                    if (sameType) {
                        RegisterBean.setFieldValue(targetField, target, value);
                    }
                }
            }*/

    public static Object testRef(Object bo) {
        if(bo == null){
            return null;
        }
        Class clz = bo.getClass();
        Field[] f = clz.getDeclaredFields();
        try {
            for (int i = 0; i < f.length; i++) {
                f[i].setAccessible(true);
                if (f[i].get(bo) == null) {
                    f[i].set(bo, "default");
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bo;
    }

@Test
public void get(){
    List<User> list= Lists.newArrayList();
    List<UserDTO> listDTO= Lists.newArrayList();
    User user =new User();
    user.setId(1);
    User user1 =new User();
    user1.setId(1);
    list.add(user1);
    list.add(user);

 UserDTO userDTO= BeanByMapper.getCopyByRefMapper(User.class, user, UserDTO.class);

    /*UserDTO userDTO = MapperFactory.getCopyByRefMapper().mapClass(User.class, UserDTO.class)
            .register()
            .map(user, UserDTO.class);*/
    listDTO= BeanByMapper.copyByRefListMapper(User.class,list,UserDTO.class);
    System.out.println("222" + JSONHelper.toJSON( listDTO));
}

}
