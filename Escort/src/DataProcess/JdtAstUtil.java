package DataProcess;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JdtAstUtil {
    public static CompilationUnit getCompilationUnit(String javaFilePath){
        byte[] input = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
            input = new byte[bufferedInputStream.available()];
                bufferedInputStream.read(input);
                bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setEnvironment(null, null, null, true);
        int index=javaFilePath.lastIndexOf("\\");
        String name=javaFilePath.substring(index,javaFilePath.length()-5);
        parser.setUnitName(name);
        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);
        parser.setSource(new String(input).toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        CompilationUnit result = (CompilationUnit) (parser.createAST(null));
        return result;
    }
}
