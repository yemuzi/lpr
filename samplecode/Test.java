
import java.util.ArrayList;
import java.util.List;

import com.niulib.aisdk.Const;
import com.niulib.aisdk.EventInfo;
import com.niulib.aisdk.LPR;
import com.niulib.aisdk.Rect;

public class Test {

	public static void main(String[] args) {
		System.out.println("\nlpr...");
        // 1.init
		LPR engine = new LPR();

		String filename = "test.jpg";
		String outFilename = "out.jpg"; // 空值表示不输出叠加后的效果图

		// 2.active
//		int retAct = engine.active("");
//		if (Const.MACT_SUCCESS == retAct) {
//			System.out.println(Const.getString(Const.MACT_SUCCESS));
//		} else if (Const.MACT_TIME_PASSED == retAct) {
//			System.out.println(Const.getString(Const.MACT_TIME_PASSED));
//		} else {
//			System.out.println(Const.getString(Const.MACT_FAILED));
//		}
		
		// 3.process
		Rect roi = null;// 
		//Rect roi = new Rect(200, 200, 1500, 1000); // 指定感兴趣分析区域,为空表示全图计算
		List<EventInfo> plates = new ArrayList<EventInfo>(); // 初始化列表
		long startTime = System.currentTimeMillis();
		int ret = engine.process(filename, outFilename, roi, plates);
		long endTime = System.currentTimeMillis();
		long usedTime = (endTime - startTime);

		if (Const.MERR_INVALID_PARAM == ret) {
			System.out.println(Const.getString(Const.MERR_INVALID_PARAM));
		} else if (Const.MERR_OUT_FILENAME_NOT_LEGAL == ret) {
			System.out.println(Const.getString(Const.MERR_OUT_FILENAME_NOT_LEGAL));
		} else if (Const.MERR_MODEL_INFERENCE_FAILED == ret) {
			System.out.println(Const.getString(Const.MERR_MODEL_INFERENCE_FAILED));
		} else if (Const.MERR_READ_IMAGE_FAILED == ret) {
			System.out.println(Const.getString(Const.MERR_READ_IMAGE_FAILED));
		} else if (Const.MERR_LOAD_MODEL_FAILED == ret) {
			System.out.println(Const.getString(Const.MERR_LOAD_MODEL_FAILED));
		} else if (Const.MERR_NORMAL == ret) {
			System.out.println(Const.getString(Const.MERR_NORMAL));
			for (int ind = 0; ind < plates.size(); ind++) {
				EventInfo info=plates.get(ind);
				System.out.println("position:"+info.getRect().toString() + ", confidence:" + info.getConfidence()+", info:"+info.getInfo());
			}
		}

		System.out.println("finish...,time:" + usedTime + " ms");
	}
}
