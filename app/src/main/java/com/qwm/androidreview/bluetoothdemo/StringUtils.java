package com.qwm.androidreview.bluetoothdemo;

/**
 * Created by wiming on 2016/5/6.
 */
public class StringUtils {

    /**
     * 获取指令
     * @param cmdint
     * @param data1
     * @param data2
     * @return
     */
    public static String getCmd(int cmdint,int data1,int data2){
        //1.计算第10个
        byte cmd = (byte) cmdint;
        byte byte10 =  cmd;
        byte[] datas1 = int2Byte(data1);
        byte[] datas2 = int2Byte(data2);
        //计算第10
        for (int i = 0; i < datas1.length; i++) {
            byte10 +=(datas1[i] + datas2[i]);
        }
        byte10 = (byte) (byte10 & 0xFF);
        //2.组装指令
        StringBuilder  sb = new StringBuilder();
        // 1    4    4     1
        sb.append(convertDecToHexString(cmd));//第1位
        sb.append(bytesToHexString(datas1));//data1
        sb.append(bytesToHexString(datas2));//data2
        sb.append(convertDecToHexString(byte10));//校验位
        return sb.toString();
    }


    /**
     * data拼接为字符串
     * @param datas
     * @return
     */
    public static String getDataStr( byte[] datas){
        StringBuilder  sb = new StringBuilder();

        for (int i = 0; i < datas.length; i++) {
            sb.append(convertDecToHexString(datas[i]));
        }

        return sb.toString();
    }


    /**
     * 单个byte转16进制
     * @param b
     * @return
     */
    public static String convertDecToHexString(byte b){
        int v =  b & 0xFF;
        String str = Integer.toHexString(v);
        if(str.length()<2){
            str = "0"+str;
        }
        return  str;
    }




    /**
     * byte数组转为对应的16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }




    /**
     * 十六进制编码字符串转为对应的二进制数组
     *
     * @param s
     * @return
     */
    public static byte[] hexStringToBytes(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baKeyword;
    }


    public static byte[] int2Byte(int data){
        byte[] datas = new byte[4];
        for (int i = 0; i < 4; i++) {
            byte bt = (byte) (data & 0xFF);
            data = data >> 8;
            datas[3-i] = bt;
        }
        return datas;
    }

}
