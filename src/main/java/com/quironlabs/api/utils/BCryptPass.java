package com.quironlabs.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//public class BCryptPass {
//        public static void main(String[] args) {
//
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            System.out.println("encoder:  " + encoder.encode("abel"));
//            System.out.println("encoder:  " + encoder.encode("admin"));
//            
//            String pass = "esteganografia_01+";
//            String passC = encoder.encode(pass);
//            System.out.println(pass + ":  " + passC);
//            
//            System.out.println("match? " + encoder.matches(pass, passC));
//            
////            System.out.println("------------华丽的分割线-----------------------");
////            String Md5Password = MD5Util.encode("abel");
////            System.out.println("Md5Password:  " + Md5Password);
////            System.out.println("encoder:  " + encoder.encode(Md5Password));
////            if (encoder.matches(Md5Password, "$2a$10$37MXEfzlbtC6QSsRTlRhIOmykMRJtO5mU8Y.yiJBjy1x4WYWFR5gG")) {
////                System.out.println("Md5Password: true");
////            }
//
//        }
//
//}
