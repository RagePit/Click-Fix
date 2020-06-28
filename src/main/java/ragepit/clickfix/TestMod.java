package ragepit.clickfix;

import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.SIPUSH;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.tree.AbstractInsnNode.METHOD_INSN;

import java.lang.reflect.Field;
import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.MethodVisitor.*;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.ReflectionHelper;


public class TestMod implements net.minecraft.launchwrapper.IClassTransformer {

	@Override
	public byte[] transform(String arg0, String arg1, byte[] arg2) {

		
		
		if (arg0.equals("ave")) {
			System.out.println("********* INSIDE OBFUSCATED MINECRAFT CLASS ABOUT TO PATCH: " + arg0);
			return patchClassASM(arg0, arg2, true);
        }
		
		if (arg0.equals("net.minecraft.client.Minecraft")) {
			System.out.println("********* INSIDE MINECRAFT CLASS ABOUT TO PATCH: " + arg0);
			return patchClassASM(arg0, arg2, false);
        }
        return arg2;
	}
	
	
	 public byte[] patchClassASM(String name, byte[] bytes, boolean obfuscated) {
	 
		 	String targetMethodName = "";
	        
	        if(obfuscated == true)
	        	targetMethodName ="aw";
	        else
	        	targetMethodName ="clickMouse";
	        

		    ClassNode classNode = new ClassNode();
	        ClassReader classReader = new ClassReader(bytes);
	        classReader.accept(classNode, 0);
	        
	        
	        @SuppressWarnings("unchecked")
	        Iterator<MethodNode> methods = classNode.methods.iterator();
	        while(methods.hasNext())
	        {
	            MethodNode m = methods.next();
	            
	            
	            int pushindex = 0;
	            int pushindex2 = 0;
	            
	            if ((m.name.equals(targetMethodName) && m.desc.equals("()V")))
	            {
	                System.out.println("********* Inside target method! " + m.name);
	                
	                
	                AbstractInsnNode currentNode = null;
	                AbstractInsnNode targetNode2 = null;
	                AbstractInsnNode targetNode = null;
	                
	                @SuppressWarnings("unchecked")
	                Iterator<AbstractInsnNode> iter = m.instructions.iterator();
	               
	                int index = -1;
	                InsnList toInject = new InsnList();
					while (iter.hasNext())
					{
						index++;
						currentNode = iter.next();
		
						if (currentNode.getOpcode() == BIPUSH)
						{

							if (pushindex == 0) {
							pushindex = index;
							targetNode = currentNode;
							} else {
								pushindex2 = index;
								targetNode2 = currentNode;
							}
							
						}

					}

	                if (pushindex == 0 | pushindex2 == 0)
	                {
	                    System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
	                    return bytes;
	                }
	                
	                System.out.println("********* pushindex1 & pushindex2 should be 29 & 96 -> " + pushindex + " & " + pushindex2);
	                AbstractInsnNode remNode1 = m.instructions.get(pushindex+1);
	                AbstractInsnNode remNode2 = m.instructions.get(pushindex);
	                AbstractInsnNode remNode3 = m.instructions.get(pushindex-1);
	                AbstractInsnNode remNode4 = m.instructions.get(pushindex-2);
	                AbstractInsnNode remNode5 = m.instructions.get(pushindex-3);
	                
	                AbstractInsnNode remNode21 = m.instructions.get(pushindex2+1);
	                AbstractInsnNode remNode22 = m.instructions.get(pushindex2);
	                AbstractInsnNode remNode23 = m.instructions.get(pushindex2-1);
	                AbstractInsnNode remNode24 = m.instructions.get(pushindex2-2);
	                AbstractInsnNode remNode25 = m.instructions.get(pushindex2-3);
	                
	                m.instructions.remove(remNode1);
	                m.instructions.remove(remNode2);
	                m.instructions.remove(remNode3);
	                m.instructions.remove(remNode4);
	                m.instructions.remove(remNode5);
	                
	                m.instructions.remove(remNode21);
	                m.instructions.remove(remNode22);
	                m.instructions.remove(remNode23);
	                m.instructions.remove(remNode24);
	                m.instructions.remove(remNode25);

	                
	                System.out.println("Patching Complete!");
	                break;
	            }
	        }
	        
	        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
	        classNode.accept(writer);
	        return writer.toByteArray();
      }
}


