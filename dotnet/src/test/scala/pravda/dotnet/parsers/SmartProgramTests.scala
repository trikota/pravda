package pravda.dotnet.parsers

import pravda.dotnet.DiffUtils
import pravda.dotnet.data.Method
import pravda.dotnet.data.TablesData._
import pravda.dotnet.parsers.CIL._
import pravda.dotnet.parsers.Signatures.SigType._
import pravda.dotnet.parsers.Signatures._
import utest._

object SmartProgramTests extends TestSuite {

  val tests = Tests {
    'smartProgramParse - {
      val Right((_, cilData, methods, signatures)) = FileParser.parseFile("smart_program.exe")

      DiffUtils.assertEqual(
        methods,
        List(
          Method(
            List(
              Nop,
              LdArg0,
              LdFld(FieldData(1, "balances", 87)),
              LdArg1,
              LdcI40,
              CallVirt(MemberRefData(TypeSpecData(20), "getDefault", 28)),
              StLoc0,
              BrS(0),
              LdLoc0,
              Ret
            ),
            3,
            Some(16)
          ),
          Method(
            List(
              Nop,
              LdArg2,
              LdcI40,
              Cgt,
              StLoc0,
              LdLoc0,
              BrFalseS(98),
              Nop,
              LdArg0,
              LdFld(FieldData(1, "balances", 87)),
              LdArg0,
              LdFld(FieldData(1, "sender", 96)),
              LdcI40,
              CallVirt(MemberRefData(TypeSpecData(20), "getDefault", 28)),
              LdArg2,
              Clt,
              LdcI40,
              Ceq,
              StLoc1,
              LdLoc1,
              BrFalseS(68),
              Nop,
              LdArg0,
              LdFld(FieldData(1, "balances", 87)),
              LdArg0,
              LdFld(FieldData(1, "sender", 96)),
              LdArg0,
              LdFld(FieldData(1, "balances", 87)),
              LdArg0,
              LdFld(FieldData(1, "sender", 96)),
              LdcI40,
              CallVirt(MemberRefData(TypeSpecData(20), "getDefault", 28)),
              LdArg2,
              Sub,
              CallVirt(MemberRefData(TypeSpecData(20), "put", 42)),
              Nop,
              LdArg0,
              LdFld(FieldData(1, "balances", 87)),
              LdArg1,
              LdArg0,
              LdFld(FieldData(1, "balances", 87)),
              LdArg1,
              LdcI40,
              CallVirt(MemberRefData(TypeSpecData(20), "getDefault", 28)),
              LdArg2,
              Add,
              CallVirt(MemberRefData(TypeSpecData(20), "put", 42)),
              Nop,
              Nop,
              Nop,
              Ret
            ),
            5,
            Some(37)
          ),
          Method(
            List(
              LdArg0,
              LdNull,
              StFld(FieldData(1, "balances", 87)),
              LdArg0,
              LdNull,
              StFld(FieldData(1, "sender", 96)),
              LdArg0,
              Call(MemberRefData(TypeRefData(6, "Object", "System"), ".ctor", 6)),
              Nop,
              Ret
            ),
            0,
            None
          ),
          Method(List(Nop, Ret), 0, None),
          Method(List(LdArg0, Call(MemberRefData(TypeRefData(6, "Object", "System"), ".ctor", 6)), Nop, Ret), 0, None),
          Method(List(LdArg0, Call(MemberRefData(TypeRefData(6, "Attribute", "System"), ".ctor", 6)), Nop, Ret),
                 0,
                 None),
          Method(List(), 0, None),
          Method(List(), 0, None),
          Method(List(), 0, None),
          Method(
            List(
              Nop,
              LdArg0,
              LdArg1,
              CallVirt(MemberRefData(TypeSpecData(56), "exists", 65)),
              LdcI40,
              Ceq,
              StLoc0,
              LdLoc0,
              BrFalseS(5),
              Nop,
              LdArg2,
              StLoc1,
              BrS(11),
              Nop,
              LdArg0,
              LdArg1,
              CallVirt(MemberRefData(TypeSpecData(56), "get", 71)),
              StLoc1,
              BrS(0),
              LdLoc1,
              Ret
            ),
            2,
            Some(50)
          ),
          Method(List(LdArg0, Call(MemberRefData(TypeRefData(6, "Object", "System"), ".ctor", 6)), Nop, Ret), 0, None),
          Method(List(LdArg0, Call(MemberRefData(TypeRefData(6, "Object", "System"), ".ctor", 6)), Nop, Ret), 0, None),
          Method(List(LdArg0, Call(MemberRefData(TypeRefData(6, "Object", "System"), ".ctor", 6)), Nop, Ret), 0, None),
          Method(List(LdArg0, Call(MemberRefData(TypeRefData(6, "Object", "System"), ".ctor", 6)), Nop, Ret), 0, None)
        )
      )

      DiffUtils.assertEqual(
        signatures.toList.sortBy(_._1),
        List(
          1 -> MethodRefDefSig(true, false, false, false, 0, Tpe(Void, false), List(Tpe(I4, false))),
          6 -> MethodRefDefSig(true, false, false, false, 0, Tpe(Void, false), List()),
          10 -> MethodRefDefSig(true,
                                false,
                                false,
                                false,
                                0,
                                Tpe(Void, false),
                                List(Tpe(ValueTpe(TypeRefData(15, "DebuggingModes", "")), false))),
          16 -> LocalVarSig(List(LocalVar(I4, false))),
          20 -> TypeSig(
            Tpe(
              Generic(
                Cls(TypeDefData(
                  1048705,
                  "Mapping`2",
                  "io.mytc.pravda",
                  Ignored,
                  Vector(),
                  Vector(
                    MethodDefData(0, 1478, "get", 71, Vector(ParamData(0, 1, "key"))),
                    MethodDefData(0, 1478, "exists", 65, Vector(ParamData(0, 1, "key"))),
                    MethodDefData(0, 1478, "put", 42, Vector(ParamData(0, 1, "key"), ParamData(0, 2, "value"))),
                    MethodDefData(0, 134, "getDefault", 28, Vector(ParamData(0, 1, "key"), ParamData(0, 2, "def"))),
                    MethodDefData(0, 6276, ".ctor", 6, Vector())
                  )
                )),
                List(Cls(
                       TypeDefData(1048577,
                                   "Address",
                                   "io.mytc.pravda",
                                   Ignored,
                                   Vector(),
                                   Vector(MethodDefData(0, 6278, ".ctor", 6, Vector())))),
                     I4)
              ),
              false
            )),
          28 -> MethodRefDefSig(true,
                                false,
                                false,
                                false,
                                0,
                                Tpe(Var(1), false),
                                List(Tpe(Var(0), false), Tpe(Var(1), false))),
          37 -> LocalVarSig(List(LocalVar(Boolean, false), LocalVar(Boolean, false))),
          42 -> MethodRefDefSig(true,
                                false,
                                false,
                                false,
                                0,
                                Tpe(Void, false),
                                List(Tpe(Var(0), false), Tpe(Var(1), false))),
          50 -> LocalVarSig(List(LocalVar(Boolean, false), LocalVar(Var(1), false))),
          56 -> TypeSig(
            Tpe(
              Generic(
                Cls(TypeDefData(
                  1048705,
                  "Mapping`2",
                  "io.mytc.pravda",
                  Ignored,
                  Vector(),
                  Vector(
                    MethodDefData(0, 1478, "get", 71, Vector(ParamData(0, 1, "key"))),
                    MethodDefData(0, 1478, "exists", 65, Vector(ParamData(0, 1, "key"))),
                    MethodDefData(0, 1478, "put", 42, Vector(ParamData(0, 1, "key"), ParamData(0, 2, "value"))),
                    MethodDefData(0, 134, "getDefault", 28, Vector(ParamData(0, 1, "key"), ParamData(0, 2, "def"))),
                    MethodDefData(0, 6276, ".ctor", 6, Vector())
                  )
                )),
                List(Var(0), Var(1))
              ),
              false
            )),
          65 -> MethodRefDefSig(true, false, false, false, 0, Tpe(Boolean, false), List(Tpe(Var(0), false))),
          71 -> MethodRefDefSig(true, false, false, false, 0, Tpe(Var(1), false), List(Tpe(Var(0), false))),
          87 -> FieldSig(
            Generic(
              Cls(TypeDefData(
                1048705,
                "Mapping`2",
                "io.mytc.pravda",
                Ignored,
                Vector(),
                Vector(
                  MethodDefData(0, 1478, "get", 71, Vector(ParamData(0, 1, "key"))),
                  MethodDefData(0, 1478, "exists", 65, Vector(ParamData(0, 1, "key"))),
                  MethodDefData(0, 1478, "put", 42, Vector(ParamData(0, 1, "key"), ParamData(0, 2, "value"))),
                  MethodDefData(0, 134, "getDefault", 28, Vector(ParamData(0, 1, "key"), ParamData(0, 2, "def"))),
                  MethodDefData(0, 6276, ".ctor", 6, Vector())
                )
              )),
              List(Cls(
                     TypeDefData(1048577,
                                 "Address",
                                 "io.mytc.pravda",
                                 Ignored,
                                 Vector(),
                                 Vector(MethodDefData(0, 6278, ".ctor", 6, Vector())))),
                   I4)
            )),
          96 -> FieldSig(
            Cls(
              TypeDefData(1048577,
                          "Address",
                          "io.mytc.pravda",
                          Ignored,
                          Vector(),
                          Vector(MethodDefData(0, 6278, ".ctor", 6, Vector()))))),
          100 -> MethodRefDefSig(
            true,
            false,
            false,
            false,
            0,
            Tpe(I4, false),
            List(
              Tpe(Cls(
                    TypeDefData(1048577,
                                "Address",
                                "io.mytc.pravda",
                                Ignored,
                                Vector(),
                                Vector(MethodDefData(0, 6278, ".ctor", 6, Vector())))),
                  false))
          ),
          106 -> MethodRefDefSig(
            true,
            false,
            false,
            false,
            0,
            Tpe(Void, false),
            List(Tpe(Cls(
                       TypeDefData(1048577,
                                   "Address",
                                   "io.mytc.pravda",
                                   Ignored,
                                   Vector(),
                                   Vector(MethodDefData(0, 6278, ".ctor", 6, Vector())))),
                     false),
                 Tpe(I4, false))
          ),
          113 -> MethodRefDefSig(false, false, false, false, 0, Tpe(Void, false), List())
        )
      )
    }
  }
}
