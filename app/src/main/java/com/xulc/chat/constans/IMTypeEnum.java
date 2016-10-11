package com.xulc.chat.constans;

/**
 * Created by 徐椋春 on 2016/8/24.
 */
public enum IMTypeEnum
    {
        /**
         * 文本消息
         */
        TEXT (1),

        /**
         * 图片消息
         */
        IMAGE (2),

        /**
         * 语音消息
         */
        AUDIO (4);
        private int _value;
        IMTypeEnum(int value) {
            _value = value;
        }

        public int value()
        {
            return _value;
        }
        public static IMTypeEnum getValue(int value){
            switch (value){
                case 1:
                    return TEXT;
                case 2:
                    return IMAGE;
                case 4:
                    return AUDIO;
                default:
                    return null;
            }
        }




    }
