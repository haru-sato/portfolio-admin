package com.seattleacademy.team20;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Controller
public class SkillController {
  private static final Logger logger = LoggerFactory.getLogger(SkillController.class);
    //private Logger logger = LoggerFactory.getLogger(SkillController.class);
    //  サンプルはprivate static final Logger logger(static変数)になっていたけど
    //  このアプリが誰に使われ、どこでデプロイされるのか分からないのなら、
    //  今のコードの形(インスタンス変数）が望ましい

    @RequestMapping(value = "/skillupload", method = RequestMethod.GET)
    // valueでupload(URLの1部分になる)を定義
    public String upload(Locale locale, Model model) throws IOExeption, IOException {
        // skillUpload.jspのこの作業をしてください
      initialize();
    	List<Skill> skills = selectSkills();
    	  //SequelProからデータをとってくる

    	  uploadSkill(skills);

        return "skillUpload";
        // skillUpload.jspの中身を返す
        // ここは関数定義してなくても、jspの前の部分だけで引っ張ってこれる
    }
    @Autowired
//  DBを取ってくるためのライブラリ
  private JdbcTemplate jdbcTemplate;

    public List<Skill> selectSkills() {
        final String sql = "select * from skills";
        return jdbcTemplate.query(sql, new RowMapper<Skill>() {
//        	ここのSkillCategoryはSkillCategory.javaから引っ張っている
            public Skill mapRow(ResultSet rs, int RowNum) throws SQLException{
                return new Skill(rs.getString("category"),
                        rs.getString("name"), rs.getInt("score"));
               }
           });
       }

   private FirebaseApp app;

   public void initialize() throws IOException {
      FileInputStream refreshToken = new FileInputStream("/Users/haru/Downloads/dev-portfolio-86e04-firebase-adminsdk-oz5vk-f3dfed7dde.json");
      FirebaseOptions options = new FirebaseOptions.Builder()
           .setCredentials(GoogleCredentials.fromStream(refreshToken))
           .setDatabaseUrl("https://dev-portfolio-86e04.firebaseio.com/")
           .build();
       app = FirebaseApp.initializeApp(options,"other");
   }
//   SDKの初期化
//   公式コピペ

   public void uploadSkill(List<Skill> skills) {
	   // データの保存
	   final FirebaseDatabase database = FirebaseDatabase.getInstance(app);
	   DatabaseReference ref = database.getReference("skills-demo");

	   // Map型のリストを作る。MapはStringで聞かれたものに対し、Object型で返すようにしている
	   List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
	   Map<String,Object> map;
	   Map<String, List<Skill>>skillMap = skills.stream().collect(Collectors.groupingBy(Skill::getCategory));
	   for(Map.Entry<String, List<Skill>> entry : skillMap.entrySet()) {
		   map = new HashMap<>();
		   map.put("category", entry.getKey());
		   map.put("skill", entry.getValue());
		   dataList.add(map);
	  }

	   ref.setValue(dataList, new DatabaseReference.CompletionListener() {
		   @Override
		   public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
			   if(databaseError != null) {
				   System.out.println("Data could be saved" + databaseError.getMessage());
			   } else {
					   System.out.println("Data save successfully.");
			   }
		   }
		});
	}
}