package demo.springboot.service.impl;

import demo.springboot.domain.Book;
import demo.springboot.domain.ResultBean;
import demo.springboot.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Book 业务层实现
 *
 * Created by bysocket on 27/09/2017.
 */
@Service
public class BookServiceImpl implements BookService {

    // 模拟数据库，存储 Book 信息
    // 第五章《﻿数据存储》会替换成 MySQL 存储
    private static Map<Long, Book> BOOK_DB = new HashMap<>();
	public BookServiceImpl(){
		BOOK_DB.put(1L, new Book(1L,"书名1"));
		BOOK_DB.put(2L, new Book(2L,"书名2"));
		BOOK_DB.put(3L, new Book(3L,"书名3"));
		BOOK_DB.put(4L, new Book(4L,"书名4"));
	}

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(BOOK_DB.values());
    }

    @Override
    public Book insertByBook(Book book) {
        book.setId(BOOK_DB.size() + 1L);
        BOOK_DB.put(book.getId(), book);
        return book;
    }

    @Override
    public Book update(Book book) {
        BOOK_DB.put(book.getId(), book);
        return book;
    }

    @Override
    public Book delete(Long id) {
        return BOOK_DB.remove(id);
    }

    @Override
    public Book findById(Long id) {
        return BOOK_DB.get(id);
    }

	@Override
	public ResultBean getAppVersion() {
		ResultBean resultBean=new ResultBean();
		
		File file=null;
		try {
			file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX +"appversion");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(file!=null&&file.exists()){
            try {
                StringBuilder sb = new StringBuilder();
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                read.close();
                resultBean.version=new Gson().fromJson(sb.toString(), ResultBean.Version.class);
                 sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
                
            }
        	
        	
        	resultBean.state=1;
        	resultBean.msg="has file";	
        	
        }else{
        	resultBean.state=0;
        	resultBean.msg="no file";
        }
		return resultBean;
	}

	@Override
	public String getGuideInfo() {
		File file=null;
		String guideInfo="{}";
		try {
			file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX +"guide");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(file!=null&&file.exists()){
            try {
                StringBuilder sb = new StringBuilder();
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                read.close();
                guideInfo=sb.toString();
                 sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
                
            }
        }
		
		return guideInfo;
	}
    
}
