import { StaffCategoryType } from './staff-category-type.enum';
import { StaffType } from './staff-type.enum';
import { FacultyCode } from './../../shared/model/common/faculty-code.interface';
import {Actor} from './actor.interface';
export interface Staff extends Actor {
    staffCategory: StaffCategoryType;
    staffType: StaffType;
    facultyCode: FacultyCode;

}
