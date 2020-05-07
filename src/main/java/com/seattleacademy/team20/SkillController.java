package com.seattleacademy.team20;	
import java.sql.ResultSet;	
import java.sql.SQLException;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Locale;	
import java.util.Map;	
	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.jdbc.core.JdbcTemplate;	
import org.springframework.jdbc.core.RowMapper;	
import org.springframework.stereotype.Controller;	
import org.springframework.transaction.annotation.Transactional;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestMethod;	

@Controller	
public class SkillController {	
    private Logger logger = LoggerFactory.getLogger(SkillController.class);	
    //  サンプルはprivate static final Logger logger(static変数)になっていたけど
    //  このアプリが誰に使われ、どこでデプロイされるのか分からないのなら、
    //  今のコードの形(インスタンス変数）が望ましい
   
    @RequestMapping(value = "/upload", method = RequestMethod.GET)	
    // valueでupload(URLになる)を定義
    public String skillUpload(Locale locale, Model model) {	
        // skillUpload.jspのこの作業をしてください
        return "skillUpload";	
        // skillUpload.jspの中身を返す
        // ここは関数定義してなくても、jspの前の部分だけで引っ張ってこれる
    }	

}