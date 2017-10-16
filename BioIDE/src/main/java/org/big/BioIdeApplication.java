package org.big;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *<p><b>Spring boot的启动类</b></p>
 *<p> Spring boot程序的启动类</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/8/31 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@SpringBootApplication
public class BioIdeApplication {

	/**
	 *<b>Spring boot的启动方法</b>
	 *<p> Spring boot的启动方法</p>
	 * @author WangTianshan (王天山)
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) throws Exception{
		SpringApplication.run(BioIdeApplication.class, args);
	}

}
