package ddm;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.junit.Test;

public class TestJava {
	@Test
	public void testManagement(){
		RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
		System.out.println(mxBean.getName());
	}
}
