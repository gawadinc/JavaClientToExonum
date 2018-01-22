var Exonum = require('exonum-client');

console.log("Hello World");
console.log(Exonum.keyPair());
var SendFunds = Exonum.newMessage({
    size: 72,
    network_id: 0,
    protocol_version: 0,
    service_id: 0,
    message_id: 0,
    fields: {
        from: {type: Exonum.Hash, size: 32, from: 0, to: 32},
        to: {type: Exonum.Hash, size: 32, from: 32, to: 64},
        amount: {type: Exonum.Uint64, size: 8, from: 64, to: 72}
    }
});
var data = {
    from: '6752be882314f5bbbc9a6af2ae634fc07038584a4a77510ea5eced45f54dc030',
    to: 'f5864ab6a5a2190666b47c676bcf15a1f2f07703c5bcafb5749aa735ce8b7c36',
    amount: 50
};

var buffer = SendFunds.serialize(data, true);
var secretKey = '978e3321bd6331d56e5f4c2bdb95bf471e95a77a6839e68d4241e7b0932ebe2b' +
 'fa7f9ee43aff70c879f80fa7fd15955c18b98c72310b09e7818310325050cf7a';
 console.log(Exonum.sign(secretKey, buffer));