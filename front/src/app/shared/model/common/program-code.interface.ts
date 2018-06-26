import {GraduateCenter} from './graduate-center.interface';
import {FacultyCode} from './faculty-code.interface';
import {MetaObject} from '../../../core/meta-object.interface';
import {ProgramLevel} from '../policy/program-level.interface';
export interface ProgramCode extends MetaObject {
  code: string;
  descriptionEn: string;
  descriptionMs: string;
  programLevel: ProgramLevel;
  graduateCenter: GraduateCenter;
  facultyCode:FacultyCode;
}
