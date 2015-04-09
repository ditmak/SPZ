package ddm;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csl.demo.entities.Persion;
import com.csl.mybatis.DAO.PersionDAO;
import com.csl.mybatis.utils.Conditions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class PersionDAOTest {

	@Autowired
	PersionDAO dao;

	@Test
	public void testFindById() {
		Persion p = dao.findEntityById(7);
		Persion p1 = dao.findEntityById(8);
		Persion p2 = dao.findEntityById(8);
		Persion p3 = dao.findEntityById(8);
		System.out.println(p);
	}

	@Test
	public void testInsert() {
		Persion p = new Persion();
		p.setId(1001);
		p.setName(1001 + "");
		p.setBirthDay(new Date());
		int count = dao.insertEntity(p);
		System.out.println(count);
		Persion p1 = dao.findEntityById(1001);
		System.out.println(p1);
		// dao.deleteById(1);
	}

	@Test
	public void testFindQuery() {

		Conditions con = new Conditions().equal("id", 1).equal("name", "2");

		// List<Persion> persions = dao.queryPersion();
		// List<Persion> persions = dao.queryPersions(map);
		// List<Persion> persions = dao.findEntity(map);
		List<Persion> persions = dao.findEntity(con);
		for (Persion persion : persions) {
			System.out.println(persion);
		}
	}

	@Test
	public void testDeleteByID() {
		Persion persion = dao.findEntityById(1001);
		System.out.println(persion);
		int count = dao.deleteById(1001);
		System.out.println(count);
		persion = dao.findEntityById(1001);
		System.out.println(persion);
	}

	@Test
	public void testDeleteByCondition() {
		Conditions con = new Conditions().gte("id", 16).lte("id", 34);
		List<Persion> list = dao.findEntity(con);
		System.out.println(list.size() + "---------");
		int count = dao.deleteByCondition(con);
		System.out.println(count + "---------");
	}

	@Test
	public void testUpdateById() {
		Persion p = new Persion();
		p.setId(8);
		p.setName("this is the another test");
		int count = dao.updateEntityByIdWithoutNull(p);
		System.out.println(count);
		p = dao.findEntityById(8);
		System.out.println(p);
	}

	@Test
	public void testCount() {
		Conditions con = new Conditions().gte("id", 36).lte("id", 54);
		int count = dao.countByCondition(con);
		System.out.println(count);
	}

	@Test
	public void testQueryByVO() {
		Persion p = new Persion();
		p.setId(78);

		List<Persion> byVo = dao.queryByVo(p);

		System.out.println(byVo);
	}
	/*
	 * @Test public void testBatchInsert(){ List<Persion> list = new
	 * ArrayList<Persion>(); for(int i = 1;i<1000;i++){ Persion p = new
	 * Persion(); p.setId(i); p.setName("name"+i); p.setBirthDay(new Date());
	 * list.add(p); } dao.batchInsert(list);
	 * 
	 * }
	 */

}
