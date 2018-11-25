package com.nowy.patchCreator;

import com.nowy.patchCreator.FileUtil;
import com.nowy.patchCreator.diff_match_patch;
import java.io.File;
import java.util.LinkedList;

public class PatchUtil {


    /**
     * java -o
     * @param args
     */
    public static void main(String args[]){
        String originFile = "C:\\Users\\Nowy\\Desktop\\react-native-version\\version0.1\\index.android.bundle";
        String newBundle = "C:\\Users\\Nowy\\Desktop\\react-native-version\\version0.3\\index.android.bundle";
        String outputPath = "C:\\Users\\Nowy\\Desktop\\react-native-version\\RNUpdate.pat";
        try {
//            for(int i = 0 ; i < args.length ; i++){
//                if(args[i].equalsIgnoreCase("-o")){//指定源文件路径
//                    originFile = args[i+1];
//                }else if(args[i].equalsIgnoreCase("-n")){//指定新文件路径
//                    newBundle = args[i+1];
//                }else if(args[i].equalsIgnoreCase("-w")){//指定输出路径
//                    outputPath = args[i+1];
//                }
//            }

            String originStr = FileUtil.readFile(new File(originFile));
            String newBundleStr = FileUtil.readFile(new File(newBundle));


            diff_match_patch dmp  = new diff_match_patch();

            LinkedList<diff_match_patch.Patch> patches = dmp.patch_make(originStr,newBundleStr);
            String patchesStr = dmp.patch_toText(patches);

            FileUtil.writeFile(new File(outputPath),patchesStr.trim());

            System.out.println("pat包已经生成！");

            String _patchesStr = FileUtil.getStringFromPat(outputPath);

            // 4.转换pat
            LinkedList<diff_match_patch.Patch> pathes = (LinkedList<diff_match_patch.Patch>) dmp.patch_fromText(_patchesStr.trim());
            // 5.与assets目录下的bundle合并，生成新的bundle
            Object[] resultArray = dmp.patch_apply(pathes, originStr);
            
            if(newBundleStr.equals((String)resultArray[0])){
                System.out.println("success");
            }

           
        }catch (Exception e){
            System.out.println("指令异常！");
            e.printStackTrace();
        }

    }
}
