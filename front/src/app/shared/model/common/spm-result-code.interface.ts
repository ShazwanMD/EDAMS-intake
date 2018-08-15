import { ResultType } from './../application/result-type.enum';
import { IntakeApplication } from './../application/intake-application.interface';
import { MetaObject } from './../../../core/meta-object.interface';
import { GradeCode } from './grade-code.interface';
import { SubjectCode } from './subject-code.interface';
import { SpmSubjectCode } from './spm-subject-code.interface';
export interface SpmResultCode extends MetaObject {
    aggregate: string;
    graduationYear: string;
    gradeCode: GradeCode;
    spmSubjectCode: SpmSubjectCode;
    application: IntakeApplication;
    resultType: ResultType;
  }