INSERT INTO sm_appregister (
        pk_appregister,
        app_desc,
        apptype,
        code,
        creationtime,
        creator,
        dr,
        fun_property,
        funtype,
        height,
        help_name,
        image_src,
        iscauserusable,
        iscopypage,
        isenable,
        mdid,
        modifiedtime,
        modifier,
        mountid,
        name,
        orgtypecode,
        own_module,
        parent_id,
        pk_group,
        resid,
        source_app_code,
        target_path,
        ts,
        uselicense_load,
        width
)
VALUES
        (
                '0001ZZZZ5ad349a7c3f0',
                '',
                1,
                '12H10101',
                '2020-04-10 14:45:54',
                '~',
                0,
                0,
                0,
                1,
                '',
                'businessprocess',
                'N',
                'N',
                'Y',
                '~',
                NULL,
                '~',
                NULL,
                '业务系统接口配置',
                'GLOBLE00000000000000',
                '12H1',
                '0001ZZZZZZ000012H101',
                '~',
                '',
                '12H10101',
                NULL,
                '2020-04-10 14:45:54',
                'Y',
                1
        );
go


insert into sm_apppage(pk_apppage,creationtime,creator,dr,iscarddefault,isdefault,modifiedtime,modifier,pagecode,pagedesc,pagename,pageurl,parent_id,parentcode,resid,ts) values('0001ZZZZ05324414aacf',null,'~',0,'N','Y',null,'~','12H10101_LIST',null,'业务系统接口配置列表页面','/nccloud/resources/uapbd/uapbd/12h10101/main/index.html#/list','0001ZZZZ5ad349a7c3f0','12H10101','12H10101_LIST','2020-04-10 14:45:54');
go
insert into sm_apppage(pk_apppage,creationtime,creator,dr,iscarddefault,isdefault,modifiedtime,modifier,pagecode,pagedesc,pagename,pageurl,parent_id,parentcode,resid,ts) values('0001ZZZZa976042f2393',null,'~',0,'Y','N',null,'~','12H10101_CARD',null,'业务系统接口配置卡片页面','/nccloud/resources/uapbd/uapbd/12h10101/main/index.html#/card','0001ZZZZ5ad349a7c3f0','12H10101','12H10101_CARD','2020-04-10 14:45:54');
go

insert into sm_appmenuitem(pk_menuitem,appcode,appid,dr,menudes,menuitemcode,menuitemname,parentcode,pk_group,pk_menu,resid,ts)
values('0001ZZZZ63db43ab4176','12H10101','0001ZZZZ5ad349a7c3f0',0,'业务系统接口配置','12H10101','uapbd','12H101','~','1004Z510000000000FFL',null,'2020-04-10 14:45:54');
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZd44b31024d71','0001ZZZZ5ad349a7c3f0','card_head','SaveGroup','button_secondary','编辑按钮组','',0,'buttongroup',0,'Y','N',null,null,'12H10101_CARD',null,null,'2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZf789694271cc','0001ZZZZ5ad349a7c3f0','card_head','SaveBtn','button_main','','保存',1,'general_btn',0,'Y','N','Ctrl+S',null,'12H10101_CARD','SaveGroup','12H10101_CARDSaveBtn','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ9f923021ddf3','0001ZZZZ5ad349a7c3f0','card_head','SaveAddBtn','button_secondary','','保存新增',2,'general_btn',0,'Y','N','Alt+S',null,'12H10101_CARD','SaveGroup','12H10101_CARDSaveAddBtn','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ36e7e927b779','0001ZZZZ5ad349a7c3f0','card_head','CancelBtn','button_secondary','','取消',4,'general_btn',0,'Y','N','Alt+Q',null,'12H10101_CARD',null,'12H10101_CARDCancelBtn','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZae00497934a5','0001ZZZZ5ad349a7c3f0','card_head','CUDGroup','button_secondary','CUD组','',5,'buttongroup',0,'Y','N','',null,'12H10101_CARD',null,null,'2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ11e284c87d1e','0001ZZZZ5ad349a7c3f0','card_head','CreateBtn','button_secondary','','新增',6,'general_btn',0,'Y','N','Ctrl+/',null,'12H10101_CARD','CUDGroup','12H10101_CARDCreateBtn','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZa5d4dede4ef6','0001ZZZZ5ad349a7c3f0','card_head','UpdateBtn','button_secondary','','修改',7,'general_btn',0,'Y','N','Alt+P',null,'12H10101_CARD','CUDGroup','12H10101_CARDUpdateBtn','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZa7eadf5c75a4','0001ZZZZ5ad349a7c3f0','card_head','DeleteBtn','button_secondary','','删除',8,'general_btn',0,'Y','N','Ctrl+Del',null,'12H10101_CARD','CUDGroup','12H10101_CARDDeleteBtn','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ0a8df1cdb5cb','0001ZZZZ5ad349a7c3f0','card_head','RefreshBtn','button_secondary','',null,8,'general_btn',0,'Y','N',null,null,'12H10101_CARD',null,null,'2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ88e7a1122d52','0001ZZZZ5ad349a7c3f0','tabs_head','addRow','button_secondary',null,'增行',29,'general_btn',0,'Y','N','Alt+N',null,'12H10101_CARD',null,'12H10101_CARDaddRow','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ641b59fdc919','0001ZZZZ5ad349a7c3f0','tabs_head','deleteRow','button_secondary',null,'删行',30,'general_btn',0,'Y','N','Alt+Del',null,'12H10101_CARD',null,'12H10101_CARDdeleteRow','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZfc4aadfece41','0001ZZZZ5ad349a7c3f0','tabs_body','expand','button_secondary',null,'展开',34,'general_btn',0,'Y','N','F4',null,'12H10101_CARD',null,'12H10101_CARDexpand','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ2a1cf143eeae','0001ZZZZ5ad349a7c3f0','tabs_body','insertRow','button_secondary',null,'插行',35,'general_btn',0,'Y','N','Alt+I',null,'12H10101_CARD',null,'12H10101_CARDinsertRow','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZae3547aa6c43','0001ZZZZ5ad349a7c3f0','tabs_body','delRow','button_secondary',null,'删行',36,'general_btn',0,'Y','N','Alt+Del',null,'12H10101_CARD',null,'12H10101_CARDdelRow','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ335e9e450d73','0001ZZZZ5ad349a7c3f0','tabs_body','pasteHere','button_secondary',null,'粘贴至此',37,'general_btn',0,'Y','N','Alt+V',null,'12H10101_CARD',null,'12H10101_CARDpasteHere','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZf46587578507','0001ZZZZ5ad349a7c3f0','tabs_body','fold','button_secondary',null,'收起',38,'general_btn',0,'Y','N','F4',null,'12H10101_CARD',null,'12H10101_CARDfold','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ783bc1224eb3','0001ZZZZ5ad349a7c3f0','tabs_body','unfold','button_secondary',null,'展开',38,'general_btn',0,'Y','N','F4',null,'12H10101_CARD',null,'12H10101_CARDunfold','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ9b4eae816389','0001ZZZZ5ad349a7c3f0','list_head','AddDelGroup','button_secondary','增删按钮组',null,0,'buttongroup',0,'Y','N',null,null,'12H10101_LIST',null,null,'2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ0bc5688f21a6','0001ZZZZ5ad349a7c3f0','list_head','AddBtn','button_main',null,'新增',1,'general_btn',0,'Y','N','Ctrl+/',null,'12H10101_LIST','AddDelGroup','12H10101_LISTAddBtn','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZf798711f2625','0001ZZZZ5ad349a7c3f0','list_head','RefreshBtn','button_secondary',null,null,13,'general_btn',0,'Y','N',null,null,'12H10101_LIST',null,null,'2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZ22e07b713e6d','0001ZZZZ5ad349a7c3f0','list_inner','edit','button_secondary',null,'修改',14,'general_btn',0,'Y','N','Alt+P',null,'12H10101_LIST',null,'12H10101_LISTedit','2020-04-10 14:45:54')
;
go
insert into sm_appbutnregister(pk_btn,appid,btnarea,btncode,btncolor,btndesc,btnname,btnorder,btntype,dr,isenable,iskeyfunc,keyboard,pageareacode,pagecode,parent_code,resid,ts) values('0001ZZZZa0ce5743f960','0001ZZZZ5ad349a7c3f0','list_inner','delete','button_secondary',null,'删除',16,'general_btn',0,'Y','N','Ctrl+Del',null,'12H10101_LIST',null,'12H10101_LISTDelBtn','2020-04-10 14:45:54')
;
go


