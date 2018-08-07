import { EmploymentType } from './employment-type.enum';
import { EmploymentWorkingDuration } from './employment-working-duration.enum';
import { EmploymentSectorCode } from './../common/employment-sector-code.interface';
import {MetaObject} from '../../../core/meta-object.interface';
export interface Employment extends MetaObject {
  current: boolean;
  startDate: Date;
  endDate: Date;
  employer: string;
  designation: string;
  employmentType: EmploymentType;
  workingDuration: EmploymentWorkingDuration;
  employmentSectorCode: EmploymentSectorCode;
  // transient
  selected?: boolean;
}