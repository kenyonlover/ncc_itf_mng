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
                '0001ZZZZ87E0B591EFDD',
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
                '0001ZZZZ4542645F8B81',
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
                '0001ZZZZ13904171EC12',
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
                '0001ZZZZEE7866E0FC92',
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
                '0001ZZZZ2510FE7A309B',
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
                '0001ZZZZE8AA8FF1FE71',
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
                '0001ZZZZ78C69DCC4D32',
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
                '0001ZZZZ5D28D07B3EBF',
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
                '0001ZZZZF58E40BA8338',
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
                '0001ZZZZ962D7FE8A610',
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
                '0001ZZZZDEEDD39DC489',
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
                '0001ZZZZ6BCD08825ACE',
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