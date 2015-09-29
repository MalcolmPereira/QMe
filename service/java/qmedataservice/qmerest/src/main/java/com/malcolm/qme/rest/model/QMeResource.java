/**
 * Name      : com.malcolm.qme.rest.model.QMeResource.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : QMeResource Base REST Resource
 */

package com.malcolm.qme.rest.model;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * @author Malcolm
 */
abstract class QMeResource extends ResourceSupport implements Serializable {
}
