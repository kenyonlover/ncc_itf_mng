INSERT INTO bd_billtype (
        pk_billtypeid,
        accountclass,
        billcoderule,
        billstyle,
        billtypename,
        canextendtransaction,
        checkclassname,
        classname,
        comp,
        component,
        datafinderclz,
        def1,
        def2,
        def3,
        dr,
        emendenumclass,
        forwardbilltype,
        isaccount,
        isapprovebill,
        isbizflowbill,
        iseditableproperty,
        isenablebutton,
        isenabletranstypebcr,
        islightbill,
        islock,
        isrejectuneditableproperty,
        isroot,
        issupportmobile,
        istransaction,
        isworkflowcanautoapprove,
        ncbrcode,
        nodecode,
        parentbilltype,
        pk_billtypecode,
        pk_group,
        pk_org,
        referclassname,
        systemcode,
        transtype_class,
        ts,
        webnodecode,
        wherestring
)
VALUES
        (
                '0001ZZZZB0FAC4C0256B',
                NULL,
                '~',
                NULL,
                '业务系统接口配置',
                'Y',
                NULL,
                NULL,
                NULL,
                'itfconfig',
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                'Y',
                'Y',
                'N',
                'N',
                'N',
                'N',
                NULL,
                NULL,
                NULL,
                'N',
                'Y',
                'N',
                'N',
                '~',
                '12H10101',
                '~',
                'HD01',
                '~',
                'GLOBLE00000000000000',
                NULL,
                'UAPBD',
                NULL,
                '2020-04-10 14:45:54',
                '~',
                NULL
        );

go

INSERT INTO pub_billaction (
        pk_billaction,
        action_type,
        actionnote,
        actionstyle,
        actionstyleremark,
        actiontype,
        constrictflag,
        controlflag,
        dr,
        finishflag,
        pk_billtype,
        pk_billtypeid,
        pushflag,
        showhint,
        ts
)
VALUES
        (
                '0001ZZZZ7A0F050B79EA',
                11,
                '~',
                '2',
                NULL,
                'APPROVE',
                'N',
                'N',
                NULL,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                NULL,
                '~',
                '2020-04-10 14:45:54'
        );
go

INSERT INTO pub_billaction (
        pk_billaction,
        action_type,
        actionnote,
        actionstyle,
        actionstyleremark,
        actiontype,
        constrictflag,
        controlflag,
        dr,
        finishflag,
        pk_billtype,
        pk_billtypeid,
        pushflag,
        showhint,
        ts
)
VALUES
        (
                '0001ZZZZ25B8F0A14647',
                30,
                '~',
                '3',
                NULL,
                'DELETE',
                'N',
                'N',
                NULL,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                NULL,
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO pub_billaction (
        pk_billaction,
        action_type,
        actionnote,
        actionstyle,
        actionstyleremark,
        actiontype,
        constrictflag,
        controlflag,
        dr,
        finishflag,
        pk_billtype,
        pk_billtypeid,
        pushflag,
        showhint,
        ts
)
VALUES
        (
                '0001ZZZZEF6B1385EC99',
                10,
                '~',
                '1',
                NULL,
                'SAVE',
                'N',
                'N',
                NULL,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                NULL,
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO pub_billaction (
        pk_billaction,
        action_type,
        actionnote,
        actionstyle,
        actionstyleremark,
        actiontype,
        constrictflag,
        controlflag,
        dr,
        finishflag,
        pk_billtype,
        pk_billtypeid,
        pushflag,
        showhint,
        ts
)
VALUES
        (
                '0001ZZZZB13A57E0BF0E',
                31,
                '~',
                '1',
                NULL,
                'SAVEBASE',
                'Y',
                'N',
                NULL,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                NULL,
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO PUB_BILLACTION (
        PK_BILLACTION,
        ACTION_TYPE,
        ACTIONNOTE,
        ACTIONSTYLE,
        ACTIONSTYLEREMARK,
        ACTIONTYPE,
        CONSTRICTFLAG,
        CONTROLFLAG,
        DR,
        FINISHFLAG,
        PK_BILLTYPE,
        PK_BILLTYPEID,
        PUSHFLAG,
        SHOWHINT,
        TS
)
VALUES
        (
                '0001ZZZZB13E8B400987',
                12,
                '~',
                '3',
                NULL,
                'UNAPPROVE',
                'N',
                'N',
                NULL,
                'Y',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                NULL,
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO PUB_BILLACTION (
        PK_BILLACTION,
        ACTION_TYPE,
        ACTIONNOTE,
        ACTIONSTYLE,
        ACTIONSTYLEREMARK,
        ACTIONTYPE,
        CONSTRICTFLAG,
        CONTROLFLAG,
        DR,
        FINISHFLAG,
        PK_BILLTYPE,
        PK_BILLTYPEID,
        PUSHFLAG,
        SHOWHINT,
        TS
)
VALUES
        (
                '0001ZZZZ24AE576CC44B',
                13,
                '~',
                '3',
                NULL,
                'UNSAVEBILL',
                'N',
                'Y',
                NULL,
                'Y',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                NULL,
                '~',
                '2020-04-10 14:45:54'
        );


go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZ78972CAA1FA1',
                'SAVE',
                'N_HD01_SAVE',
                0,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                '~',
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZBAACB63E8F31',
                'APPROVE',
                'N_HD01_APPROVE',
                0,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                '~',
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZB804F860EDC6',
                'UNSAVEBILL',
                'N_HD01_UNSAVEBILL',
                0,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                '~',
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZAEEE38D01FBE',
                'UNAPPROVE',
                'N_HD01_UNAPPROVE',
                0,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                '~',
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZ3B7B34724493',
                'DELETE',
                'N_HD01_DELETE',
                0,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                '~',
                '~',
                '2020-04-10 14:45:54'
        );

go

INSERT INTO PUB_BUSICLASS (
        PK_BUSICLASS,
        ACTIONTYPE,
        CLASSNAME,
        DR,
        ISBEFORE,
        PK_BILLTYPE,
        PK_BILLTYPEID,
        PK_BUSINESSTYPE,
        PK_GROUP,
        TS
)
VALUES
        (
                '0001ZZZZ26C79E702CA4',
                'SAVEBASE',
                'N_HD01_SAVEBASE',
                0,
                'N',
                'HD01',
                '0001ZZZZB0FAC4C0256B',
                '~',
                '~',
                '2020-04-10 14:45:54'
        );
go