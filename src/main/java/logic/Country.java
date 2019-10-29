/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.text.Collator;
import java.util.Comparator;

/**
 *
 * @author hecto
 */
public class Country {
        private String iso;
        private String code;
        private String name;

        Country(String iso, String code, String name) {
            this.iso = iso;
            this.code = code;
            this.name = name;
        }

        public String toString() {
            return iso + " - " + code + " - " + name.toUpperCase();
        }
        
        public String getName(){
            return this.name;
        }
    }