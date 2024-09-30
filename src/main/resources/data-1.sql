
<div class="linkparentcss"><img src="http://localhost:8091/svg/check-2.svg">
<div id="VAVBLNPJOQNQQOOHBKULKOCASUQVDPFCQSJQVYWKBCHKBELPLI" class="linkcss" 
targetpop="thisid" actiongo="__fpages" params="thisid">ECOMMERCE</div></div>

<div class="mnlinkparentcss"><img src="http://localhost:8091/svg/check-2.svg">
<div id="DDYGPGTPNWQEYIVJPFCVJQGSXGSQYVMCKVRVSKYNKWEWHPLGWG" class="mnlinkcss" 
targetpop="__content__" 
actiongo="__fpaged" params="http://localhost:8091/erp">T_AMGREAT_APPS</div></div>

create table  t_form_template( id varchar(50), pid varchar(50), viewType varchar(50), view_template text, defaultscreen varchar(50), status varchar(50),CREATEDBY VARCHAR(50), CREATEDDATE TIMESTAMP, UPDATEDBY VARCHAR(50), UPDATEDDATE TIMESTAMP, DELFLAG VARCHAR(50));

insert into t_form(id, pid, label, description, tablename)
values('t_form_list_1','','A List of Forms', 'list form for menu view', 't_form');

insert into t_form_template(id, pid, viewtype, view_template) 
values('amgreatappmenu1','amgreatappsid', 'viewtype0001',
'<div class=linkparentcss >[[ReplaceCmd]]<img src =http://localhost:8091/svg/check-2.svg /><div id=[[__ID]]  class=linkcss targetpop=thisid actiongo=__fpages params=[[__ID]] >[[__LABEL]]</div>[[ENDReplaceCmd]]</div>');

insert into t_form_template(id, pid, viewType, view_template) 
values('t_form_template_form_list_1','t_form_list_1','viewtype0001',
'<div class=mnlinkparentcss >[[ReplaceCmd]]<img src =http://localhost:8091/svg/check-2.svg /><div id=[[__ID]] class=mnlinkcss targetpop=__content__  actiongo=__fpaged params=http://localhost:8091/amgreat/portal/[[__ID]] >[[__LABEL]]</div>[[ENDReplaceCmd]]</div>');

insert into t_form_columns(id, pid, label, tbl_col_nm, t_col_id, seqno)
values('t_form_list_1_child_1','t_form_list_1','ID','ID', 'UQAKDCMTDSAJUCUNUAGQNFKVIFVUTRBKQGNQFWPGFIBVAXVAVB', 1);

insert into t_form_columns(id, pid, label, tbl_col_nm, t_col_id, seqno)
values('t_form_list_1_child_2','t_form_list_1','LABEL','LABEL', 'UQAKDCMTDSAJUCUNUAGQNFKVIFVUTRBKQGNQFWPGFIBVAXVAVB', 2);

insert into t_form_columns(id, pid, label, tbl_col_nm, t_col_id, seqno)
values('t_form_list_1_child_3','t_form_list_1','DESCRIPTION','DESCRIPTION', 'UQAKDCMTDSAJUCUNUAGQNFKVIFVUTRBKQGNQFWPGFIBVAXVAVB', 3);

insert into t_menu(id, pid, menu_level, label, description, formid)
values('t_menu_child_1','amgreatappsid',)

create table t_template(id varchar(50), pid varchar(50), label varchar(200),  template text);
insert into t_template(id,label,template)
values('viewtype0002','Template for Search Page',
'
<div class="box-100 m-2 h-4 mt-0 overflow b1" > 
					<div class="box-100 m-0 p-8 b1 txr" id="[[__IDHEADER]]">
						<div class="tablecss overflow">
							<div class="box-100 rowplaincss"><div class="col-2 txl p-8">[[__FORMLABEL]]</div><div class="col-2 txr p-8">[[MENU]]</div></div>
						</div>
					</div>
					<div class="box-100 m-0 h-8 p-8 txc" style="justify-content:center;" id="[[__CONTENT]]">
						<div class="tablecss p-4 overflow" >
								<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8">[[__PAGING]]</div></div>
								<div class="box-100 rowhcss" id="[[COLUMNS]]"><div class="colnocss txc">No</div> <div class="colcss">Label</div> </div>
								<RECORDS class="col-1">
								<div class="box-100 rowcss" id=""><div class="colnocss txc"></div> <div class="colnorecscss">no record found.</div></div>
								</RECORDS>
								<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8"></div></div>
								<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8">[[__BUTTON]]</div></div>
						</div>
					</div>
					<div class="box-95 m-0 p-8  txr"> </div>	
				</div>
'
);

insert into t_template(id,label,template)
values('viewtype0003','Template for Add Page',
'
<div class="box-100 m-2 h-4 mt-0 p-8 overflow b1" > 
	<div class="box-100 m-0 p-8 b1 txr" id="[[__IDHEADER]]">
		<div class="tablecss overflow">
			<div class="box-100 rowplaincss"><div class="col-2 txl p-8">[[__FORMLABEL]]</div><div class="col-2 txr p-8">[[MENU]]</div></div>
		</div>
	</div>
	<div class="box-100 m-0 h-8 p-8 txc" style="justify-content:center;" id="[[__CONTENT]]">
		<div class="tablecss p-4 overflow" >
				<PAGEFORM class="col-1">
					<div class="box-100 rowcss"><div class="col-4 txl">ID:</div><div class="col-4 txc"></div><div class="col-4 txl">View Type:</div><div class="col-4 txc"></div> </div>
					<div class="box-100 rowcss"><div class="col-4 txl">PID:</div><div class="col-4 txc"></div><div class="col-4 txl">View Template:</div><div class="col-4 txc"></div> </div>
				</PAGEFORM>
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8"></div></div>
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8">[[BUTTON]]</div></div>
		</div>
	</div>
	<div class="box-95 m-0 p-8  txr"> </div>	
</div>
');

insert into t_template(id,label,template)
values('viewtype0004','Template for Edit Page',
'
<div class="box-100 m-2 h-4 mt-0 p-8 overflow b1" > 
	<div class="box-100 m-0 p-8 b1 txr" id="[[__IDHEADER]]">
		<div class="tablecss overflow">
			<div class="box-100 rowplaincss"><div class="col-2 txl p-8">[[__FORMLABEL]]</div><div class="col-2 txr p-8">[[MENU]]</div></div>
		</div>
	</div>
	<div class="box-100 m-0 h-8 p-8 txc" style="justify-content:center;" id="[[__IDCONTENT]]">
		<div class="tablecss p-4 overflow" >
				<PAGEFORM class="col-1">
					<div class="box-100 rowcss"><div class="col-4 txl">ID:</div><div class="col-4 txc"></div><div class="col-4 txl">View Type:</div><div class="col-4 txc"></div> </div>
					<div class="box-100 rowcss"><div class="col-4 txl">PID:</div><div class="col-4 txc"></div><div class="col-4 txl">View Template:</div><div class="col-4 txc"></div> </div>
				</PAGEFORM>
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8"></div></div>
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8">[[BUTTON]]</div></div>
		</div>
	</div>
	<div class="box-95 m-0 p-8  txr"> </div>	
</div>
');


insert into t_template(id,label,template)
values('viewtype0005','Template for Delete Page',
'
<div class="box-100 m-2 h-4 mt-0 p-8 overflow b1" > 
	<div class="box-100 m-0 p-8 b1 txr" id="[[__IDHEADER]]">
		<div class="tablecss overflow">
			<div class="box-100 rowplaincss"><div class="col-2 txl p-8">[[__FORMLABEL]]</div><div class="col-2 txr p-8">[[MENU]]</div></div>
		</div>
	</div>
	<div class="box-100 m-0 h-8 p-8 txc" style="justify-content:center;" id="[[__IDCONTENT]]">
		<div class="tablecss p-4 overflow" >
				<VIEW class="col-1">
					<div class="box-100 rowcss"><div class="col-4 txl">ID:</div><div class="col-4 txc"></div><div class="col-4 txl">View Type:</div><div class="col-4 txc"></div> </div>
					<div class="box-100 rowcss"><div class="col-4 txl">PID:</div><div class="col-4 txc"></div><div class="col-4 txl">View Template:</div><div class="col-4 txc"></div> </div>
				</VIEW>
				
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8"></div></div>
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8">[[BUTTON]]</div></div>
		</div>
	</div>
	<div class="box-95 m-0 p-8  txr"> </div>	
</div>
');

insert into t_template(id,label,template)
values('viewtype0006','Template for Button',
'
<div  class="buttoncss" targetpop="[[TARGETPOP]]" actiongo="[[TARGETFUNCTION]]" params="[[PARAMS]]">[[LABEL]]</div>
');

//---view template: t_form_template ---

/*

 * 1. viewtype0001 => restresponse 
 * 2. view on page = viewtype0002 => search
 * 3. view on page = viewtype0003 => add
 * 4. view on page = viewtype0004 => edit
 * 5. view on page = viewtype0005 => delete
*/
---start: viewtype0001 ---
<div class="box-95 m-2 mt-0 p-8 overflow"> 
	<div class="box-95 m-0 p-8 b1 txr" id="[[__IDHEADER]]">
		<div class="tablecss overflow">
			<div class="box-100 rowplaincss"><div class="col-2 txl p-8">[[__FORMLABEL]]</div><div class="col-2 txr p-8">[[__MENU]]</div></div>
		</div>
	</div>
	<div class="box-95 m-0 h-8 p-8 txc" style="justify-content:center;" id="[[__IDCONTENT]]"> [[__CONTENT]] <br/>
		<div class="tablecss p-4 overflow" >
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8">[[__PAGING]]</div></div>
				<div class="box-100 rowhcss"><div class="colnocss txc">No</div> <div class="colcss">Label</div> </div>
				<div class="box-100 rowcss"><div class="colnocss txc"></div> <div class="colnorecscss">no record found.</div></div>
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8"></div></div>
				<div class="box-100 rowplaincss"><div class="colnorecscss txr p-8" id="[[__IDBUTTON]]">[[__BUTTON]]</div></div>
		</div>
	</div>
	<div class="box-95 m-0 p-8  txr"> </div>	
</div>
---end---	
				
				

CSS code:

space: \20
nbsp: \a0
en space: \2002
em space: \2003
3 per em space: \2004
4 per em space: \2005
6 per em space: \2006
figure space: \2007
punctuation space: \2008
thin space: \2009
hair space: \200a
zero width space: \200b
narrow nbsp: \202f
medium mathematical space: \205f
zero width nbsp: \feff