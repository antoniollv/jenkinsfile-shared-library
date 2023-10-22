package com.moradoresre.utils

class traductionUtils {

    private Map traductions = [  
        //dependencias de core
        PROJECT_BACKEND_VERSION: 'core_tron_be_nwt',
        NEWTRON_BE_VERSION: 'core_tron_be_nwt',
        NWT_CMN_API_VERSION: 'core_tron_cmn_api',
        'version.twframework': 'core_tron_fw_tw',
        'version.twclient.trn': 'core_tron_client_tw',
        'version.twclient.tst': 'core_tron_client_tst_tw',
        'version.twbom': 'core_tron_bom_tw'                      
    ]

    def getTraductions() {
        return traductions
    }
}