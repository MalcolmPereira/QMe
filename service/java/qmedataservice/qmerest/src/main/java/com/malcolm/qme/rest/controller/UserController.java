/**
 * Name      : com.malcolm.qme.rest.controller.UserController.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeUse
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.UserAPI;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;
import com.malcolm.qme.rest.exception.QMeResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Malcolm
 */
@RestController
public class UserController implements UserAPI {

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeUserDetail> list() throws QMeResourceException {
        return null;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchById(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceException {
        return null;
    }

    @RequestMapping(value=NAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchByUserName(@PathVariable(NAME_PARAM_STRING) String userName) throws QMeResourceException {
        return null;
    }

    @RequestMapping(value=EMAIL_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchByUserEmail(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceException {
        return null;
    }

    @RequestMapping(value=REGISTER_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail create(QMeUser user) throws QMeResourceException {
        return null;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail update(@PathVariable(ID_PARAM_STRING) Long userId, QMeUser user) throws QMeResourceException {
        return null;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceException {

    }
}
