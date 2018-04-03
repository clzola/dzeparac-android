package me.dzeparac.dzeparac.repositories;

import java.util.ArrayList;

import me.dzeparac.dzeparac.models.Category;

/**
 * Created by clzola on 11/18/17.
 */

/*


[
    {
        "Id": -1,
        "Name": "Dobit",
        "Icon": "https://thumb.ibb.co/cTouNm/get_money.png"
    },
    {
        "Id": 1,
        "Name": "Slatkisi",
        "Icon": "https://thumb.ibb.co/nknqTR/lollipop.png"
    },
    {
        "Id": 2,
        "Name": "Igracke",
        "Icon": "https://thumb.ibb.co/iasPoR/nutcracker.png"
    },
    {
        "Id": 3,
        "Name": "Garderoba",
        "Icon": "https://thumb.ibb.co/k3AYhm/hoodie.png"
    },
    {
        "Id": 4,
        "Name": "Igrice",
        "Icon": "https://thumb.ibb.co/jMy8F6/game_console.png"
    },
    {
        "Id": 5,
        "Name": "Elektronika",
        "Icon": "https://thumb.ibb.co/fhtEoR/cpu.png"
    },
    {
        "Id": 6,
        "Name": "Knjige",
        "Icon": "https://thumb.ibb.co/mickv6/scholarship.png"
    },
    {
        "Id": 7,
        "Name": "Slatkisi",
        "Icon": "https://thumb.ibb.co/nknqTR/lollipop.png"
    },
    {
        "Id": 8,
        "Name": "TestTest",
        "Icon": "testtest"
    }
]

 */

public class CategoriesRepository {
    public ArrayList<Category> categories;

    public CategoriesRepository() {
        categories = new ArrayList<>();
        categories.add(new Category(-1, "Dobit", "https://thumb.ibb.co/cTouNm/get_money.png"));
        categories.add(new Category(1, "Slatkisi", "https://thumb.ibb.co/nknqTR/lollipop.png"));
        categories.add(new Category(2, "Igracke", "https://thumb.ibb.co/iasPoR/nutcracker.png"));
        categories.add(new Category(3, "Garderoba", "https://thumb.ibb.co/jMy8F6/game_console.png"));
        categories.add(new Category(4, "Igrice", "https://thumb.ibb.co/cTouNm/get_money.png"));
        categories.add(new Category(5, "Elektronika", "https://thumb.ibb.co/fhtEoR/cpu.png"));
        categories.add(new Category(6, "Knjige", "https://thumb.ibb.co/mickv6/scholarship.png"));
    }
}
