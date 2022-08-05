package com.itheima.common.util;

import org.trie4j.patricia.PatriciaTrie;

public class Sample {
    public static void main(String[] args) throws Exception {
        PatriciaTrie pat = new PatriciaTrie();
        pat.insert("黑马");
        pat.insert("黑马程序员");
        pat.insert("黑猫");
        pat.insert("黑脸!");
        Iterable<String> wo = pat.predictiveSearch("黑马");// -> {"Wonder", "Wonderful!", "World"} as Iterable<String>
        for (String s : wo) {
            System.out.println(s);
        }
    }
}