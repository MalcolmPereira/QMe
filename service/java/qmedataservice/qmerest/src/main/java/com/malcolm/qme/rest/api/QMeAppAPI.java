/**
 * Name      : com.malcolm.qme.rest.api.QMeAPI.java
 * Date      : 9/28/15
 * Developer : Malcolm
 * Purpose   : QME REST API
 */
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.exception.QMeResourceException;
import org.springframework.hateoas.Resources;

/**
 * @author Malcolm
 */
@SuppressWarnings("ALL")
public interface QMeAppAPI extends QMeAPI {

    /**
     * QMe API Root Path
     */
    String ROOT_PATH = APP_STRING + "/api";

    /**
     * QMe API
     */
    String QME_API = "Qme API";

    /**
     * QMe User API
     */
    String QME_USER_API = "Qme User API";
    /**
     * User
     */
    String USER = "Get_All_User_list";
    /**
     * User Count
     */
    String USER_COUNT = "Get_User_Count";
    /**
     * User Paged
     */
    String USER_PAGED = "Get_Paged_User_list - (sortfields:USERNAME,EMAIL,FIRSTNAME,LASTNAME,REGISTERDATE,LOGINDATE)";
    /**
     * User by ID
     */
    String USER_BY_ID = "Get_User_by_id";
    /**
     * User by Name
     */
    String USER_BY_NAME = "Get_User_by_name";
    /**
     * User by Email
     */
    String USER_BY_EMAIL = "Get_User_by_email";
    /**
     * Register New User
     */
    String REGISTER_USER = "Post_New_User";
    /**
     * Stage New User
     */
    String STAGE_USER = "Post_Stage_New_User";
    /**
     * Confirm Staged User
     */
    String CONFIRM_STAGE_USER = "Post_Confirm_Stage_User";
    /**
     * Update User
     */
    String UPDATE_USER = "Put_Update_User";
    /**
     * Delete User
     */
    String DELETE_USER = "Delete_User";
    /**
     * Forgot User Name
     */
    String FORGOT_USER_NAME = "Get_Forgot_User_Name";
    /**
     * Forgot User Password
     */
    String FORGOT_USER_PASSWORD = "Put_Forgot_User_Password";
    /**
     * Reset User Password
     */
    String RESET_USER_PASSWORD = "Put_Reset_User_Password";

    /**
     * QMe Category API
     */
    String QME_CATEGORY_API = "Qme Category API";
    /**
     * Category
     */
    String CATEGORY = "Get_Category_list";
    /**
     * Category Count
     */
    String CATEGORY_COUNT = "Get_Category_Count";
    /**
     * Category Paged
     */
    String CATEGORY_PAGED = "Get_Paged_Category_list - (sortfields:CATEGORYNAME)";
    /**
     * Category by ID
     */
    String CATEGORY_BY_PARENT_ID = "Get_Category_by_parent_id";
    /**
     * Category by ID
     */
    String CATEGORY_BY_ID = "Get_Category_by_id";
    /**
     * Category by Name
     */
    String CATEGORY_BY_NAME = "Get_Category_by_name";
    /**
     * Create Category
     */
    String CREATE_CATEGORY = "Post_New_Category";
    /**
     * Update Category
     */
    String UPDATE_CATEGORY = "Put_Update_Category";
    /**
     * Delete Category
     */
    String DELETE_CATEGORY = "Delete_Category";

    /**
     * QMe Question API
     */
    String QME_QUESTION_API = "Qme Question API";
    /**
     * Question
     */
    String QUESTION = "Get_Question_list";
    /**
     * Question Count
     */
    String QUESTION_COUNT = "Get_Question_Count";
    /**
     * Question Paged
     */
    String QUESTION_PAGED = "Get_Paged_Question_list - (sortfields:QUESTION,USERNAME,QUESTIONCREATEDATE,QUESTIONUPDATEDATE)";
    /**
     * Question by Question ID
     */
    String QUESTION_BY_ID = "Get_Question_by_id";
    /**
     * Create Question
     */
    String CREATE_QUESTION = "Post_New_Question";
    /**
     * Update Question
     */
    String UPDATE_QUESTION = "Put_Update_Question";
    /**
     * Delete Question
     */
    String DELETE_QUESTION = "Delete_Question";

    /**
     * QMe Quiz API
     */
    String QME_QUIZ_API = "Qme Quiz API";
    /**
     * Quiz
     */
    String QUIZ = "Get_Quiz_list";
    /**
     * Quiz Count
     */
    String QUIZ_COUNT = "Get_Quiz_Count";
    /**
     * Quiz Paged
     */
    String QUIZ_PAGED = "Get_Paged_Quiz_list - (sortfields:QUIZ,USERNAME,QUIZCREATEDATE,QUIZUPDATEDATE)";
    /**
     * Quiz by Quiz ID
     */
    String QUIZ_BY_ID = "Get_Quiz_by_id";
    /**
     * Create Quiz
     */
    String CREATE_QUIZ = "Post_New_Quiz";
    /**
     * Update Quiz
     */
    String UPDATE_QUIZ = "Put_Update_Quiz";
    /**
     * Delete Quiz
     */
    String DELETE_QUIZ = "Delete_Quiz";


    /**
     * QMe User Quiz API
     */
    String QME_USER_QUIZ_API = "Qme User Quiz API";
    /**
     * User Quiz
     */
    String USER_QUIZ = "Get_User_Quiz_list";
    /**
     * User Quiz Count
     */
    String USER_QUIZ_COUNT = "Get_User_Quiz_Count";
    /**
     * User Quiz Paged
     */
    String USER_QUIZ_PAGED = "Get_Paged_User_Quiz_list - (sortfields:QUIZ_NAME)";
    /**
     * User Quiz by User Quiz ID
     */
    String USER_QUIZ_BY_ID = "Get_User_Quiz_by_id";

    /**
     * List QMe App API
     *
     * @return Map of QMe App Endpoints
     */
    Resources api() throws QMeResourceException;
}
