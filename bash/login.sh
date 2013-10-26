#!/bin/sh
echo "Through sshpass automatic login"
echo "please enter host suffix "
while true
do
    echo -n "'host suffix':"
    read host_suffix
    case $host_suffix in
    188)
        sshpass -p "4nfi234bnnlf234" ssh heycola@210.14.146.188 -p 2211
    break;;
    187)
        sshpass -p "OLU2FTxCAeMR" ssh root@210.14.146.187 -p 22
    break;;
    185)
        sshpass -p "821169698077" ssh lmycache@210.14.146.185 -p 2211
    break;;
    186)
        sshpass -p "4nfi234bnnlf234" ssh lmycache@210.14.146.186 -p 2211
    break;;
    178)
        sshpass -p "login*db01*136232" ssh lj-db01@210.14.146.178 -p 2211
    break;;
    179)
        sshpass -p "login*db02*259874" ssh lj-db02@210.14.146.179 -p 2211
    break;;
    c)
        sshpass -p "ljyun+c+order+2012" ssh ljyun_order@218.249.131.238 -p 2211
    break;;
    224)
        sshpass -p "224test+svn+343447" ssh test-svn@192.168.9.224 -p 2211
    break;;
    225)
        sshpass -p "test2013" ssh test@192.168.9.225 -p 2211
    break;;
    226)
        sshpass -p "test2013" ssh test@192.168.9.226 -p 2211
    break;;
    15.15)
        sshpass -p "spider_2012server" ssh spider@192.168.15.15 -p 2211
    break;;
    15.101)
        sshpass -p "order+service+548578i" ssh s-order@192.168.15.101 -p 2211
    break;;
    lize)
        sshpass -p "l2i0z1e2c0a5i3w1u" ssh lizessh@218.240.49.94
    break;;
    A)
        sshpass -p "jkh4620!" ssh root@192.168.11.201 -p 2211
        echo 111
    break;;
    B)
        sshpass -p "jkh4620!" ssh root@192.168.11.202 -p 2211
    break;;
    C)
        sshpass -p "ljyun+c+order+2012" ssh ljyun_order@192.168.15.100 -p 2211
    break;;
    D)
        sshpass -p "jkh4620!" ssh root@192.168.11.223 -p 2211
    break;;
    esac
done
exit 0

