package minimac;

import java.util.*;

class ParserUtils {
    protected static Integer parseInteger(Iterator<String> it) throws Exception {
        if (it.hasNext()) {
            return Integer.parseInt(it.next());
        } else {
            throw new Exception("Parsing error, integer expected");
        }
    }
}

public class MiniMacParser extends ParserUtils {

    static public List<Instruction> parse(String program) throws Exception {
        List<Instruction> result = new ArrayList<Instruction>();
        // split program into a list of tokens (i.e. numbers and operations):
        List<String> tokens = Arrays.asList(program.split("\\s*;\\s*|\\s+"));
        Iterator<String> it = tokens.iterator();
        while(it.hasNext()) {
            result.add(parseNext(it));
        }
        return result;
    }

    // dispatcher method:
    static public Instruction parseNext(Iterator<String> it) throws Exception {
        String opcode = it.next();
        if (opcode.equalsIgnoreCase("halt")) return new Halt();
        if (opcode.equalsIgnoreCase("add")) return parseAdd(it);
        if (opcode.equalsIgnoreCase("mul")) return parseMul(it);
        if (opcode.equalsIgnoreCase("load")) return parseLoad(it);
//        if (opcode.equalsIgnoreCase("loop")) return parseLoop(it);
//        if (opcode.equalsIgnoreCase("Bgt")) return parseBgt(it);
//        if (opcode.equalsIgnoreCase("Blt")) return parseBlt(it);
//        if (opcode.equalsIgnoreCase("{")) return parseBlock(it);
        if (opcode.equalsIgnoreCase("}")) return null; // used to indicate the end of a block
        throw new Exception("Parsing error, unrecognized operator: " + opcode);
    }

    static protected Add parseAdd(Iterator<String> it) throws Exception {
        int src1 = parseInteger(it);
        int src2 = parseInteger(it);
        int dest = parseInteger(it);
        return new Add(src1, src2, dest);
    }

    static protected Multiply parseMul(Iterator<String> it) throws Exception {
        int src1 = parseInteger(it);
        int src2 = parseInteger(it);
        int dest = parseInteger(it);
        return new Multiply(src1, src2, dest);
    }

    static protected Load parseLoad(Iterator<String> it) throws Exception {
        int location = parseInteger(it);
        int value = parseInteger(it);
        return new Load(value, location);
    }

    static protected Halt parseHalt() {
        return new Halt();
    }
    // etc.

}