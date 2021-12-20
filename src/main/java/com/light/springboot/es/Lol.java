package com.light.springboot.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 英雄联盟游戏人物
 *
 * @author haopeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lol {

    private Long id;
    /**
     * 英雄游戏名字
     */
    private String name;
    /**
     * 英雄名字
     */

    private String realName;
    /**
     * 英雄描述信息
     */
    private String desc;
}