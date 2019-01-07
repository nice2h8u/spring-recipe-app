package com.nicetoh8u.springrecipeapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();

    }

    @Test
    public void getId() {
        Long checkedId = 4L;

        category.setId(checkedId);
        assertEquals(checkedId,category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}