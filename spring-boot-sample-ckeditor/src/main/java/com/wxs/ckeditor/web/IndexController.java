/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wxs.ckeditor.web;

import com.wxs.ckeditor.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView index(org.springframework.ui.Model model){
		Student student=new Student("AA","1","abcdljaldf");
		model.addAttribute("status","Hello");
		model.addAttribute("page",student);
		return new ModelAndView("/index");
	}

	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	public ModelAndView save(Model model, Student student){
		System.out.println(student);
		model.addAttribute("page",student);
		model.addAttribute("status","SUCCESS");
		return new ModelAndView("/index");
	}

}
