import { MetaObject } from './../../../core/meta-object.interface';
import { GradeCode } from './grade-code.interface';
import { SubjectCode } from './subject-code.interface';
export interface SpmResultCode extends MetaObject {
    code: string;
    aggregate: string;
    graduationYear: string;
    gradeCode: GradeCode;
    subjectCode: SubjectCode;
  }