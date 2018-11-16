package demo.springboot.web;

import demo.springboot.domain.Book;
import demo.springboot.domain.ResultBean;
import demo.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Book 控制层
 *
 * Created by bysocket on 27/09/2017.
 */
@RestController
@RequestMapping(value = "/app")
public class VersionController {

    @Autowired
    BookService bookService;
    
    
    
    @RequestMapping(value = "/version",method = RequestMethod.GET)
    public ResultBean getBookList() {
        return bookService.getAppVersion();
    }
    @RequestMapping(value = "/guide", method = RequestMethod.GET)
    public String postBook() {
        return bookService.getGuideInfo();
    }
}
