import { UMKCEEStaffEditorDialog } from './dialog/umkcee-staff-editor.dialog';
import { MdDialog, MdDialogRef, MdDialogConfig } from '@angular/material';
import { SetupModuleState } from './../index';
import { Store } from '@ngrx/store';
import { SetupActions } from './../setup.action';
import { Staff } from './../../identity/staff.interface';
import { Observable } from 'rxjs/Rx';
import { Component, OnInit, ViewContainerRef, ChangeDetectionStrategy } from '@angular/core';


@Component({
    selector: 'pams-umkcee-staff-list.page',
    templateUrl: './umkcee-staff-list.page.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class UMKCEEStaffListPage implements OnInit {
    private STAFFS = "setupModuleState.staffs".split('.');
    private staffs$: Observable<Staff[]>;
    private creatorDialogRef: MdDialogRef<UMKCEEStaffEditorDialog>;


    constructor(private actions: SetupActions,
        private store: Store<SetupModuleState>,
        private vcf: ViewContainerRef,
        private dialog: MdDialog) {
        this.staffs$ = this.store.select(...this.STAFFS);
    }

    ngOnInit(): void {
        this.store.dispatch(this.actions.findUMKCEEStaffs());
        this.store.dispatch(this.actions.changeTitle("Staff"));
    }

    createDialog(): void {
        this.showDialog(null);
    }
    editDialog(staff: Staff): void {
        this.showDialog(staff);
    }

    private showDialog(staff: Staff): void {
        console.log("create");
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '70%';
        config.height = '65%';
        config.position = { top: '0px' };
        this.creatorDialogRef = this.dialog.open(UMKCEEStaffEditorDialog, config);
        if (staff) this.creatorDialogRef.componentInstance.staff = staff; // set
        this.creatorDialogRef.afterClosed().subscribe(res => {
            console.log("close dialog");
        });
    }
}