package com.web3j_intro;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class ERC20 extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610d4c806100206000396000f3006080604052600436106100da5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde0381146100df578063095ea7b31461016957806318160ddd146101a15780631d509afd146101c857806323b872dd146101eb578063313ce5671461021557806339509351146102405780635fd4b08a1461026457806370a082311461028557806384da92a7146102a657806395d89b41146102ff578063a457c2d714610314578063a9059cbb14610338578063c89109131461035c578063dd62ed3e14610400575b600080fd5b3480156100eb57600080fd5b506100f4610427565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561012e578181015183820152602001610116565b50505050905090810190601f16801561015b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561017557600080fd5b5061018d600160a060020a036004351660243561045e565b604080519115158252519081900360200190f35b3480156101ad57600080fd5b506101b66104a0565b60408051918252519081900360200190f35b3480156101d457600080fd5b506101e9600160a060020a03600435166104a7565b005b3480156101f757600080fd5b5061018d600160a060020a0360043581169060243516604435610599565b34801561022157600080fd5b5061022a610606565b6040805160ff9092168252519081900360200190f35b34801561024c57600080fd5b5061018d600160a060020a036004351660243561060b565b34801561027057600080fd5b506100f4600160a060020a0360043516610684565b34801561029157600080fd5b506101b6600160a060020a036004351661072f565b3480156102b257600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101e994369492936024939284019190819084018382808284375094975061074a9650505050505050565b34801561030b57600080fd5b506100f461077c565b34801561032057600080fd5b5061018d600160a060020a03600435166024356107b3565b34801561034457600080fd5b5061018d600160a060020a03600435166024356107fe565b34801561036857600080fd5b5061037d600160a060020a0360043516610814565b604051808060200183151515158152602001828103825284818151815260200191508051906020019080838360005b838110156103c45781810151838201526020016103ac565b50505050905090810190601f1680156103f15780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561040c57600080fd5b506101b6600160a060020a03600435811690602435166108be565b60408051808201909152600881527f4d6f696e436f696e000000000000000000000000000000000000000000000000602082015281565b6000600160a060020a038316151561047557600080fd5b50336000908152600160208181526040808420600160a060020a039690961684529490529290205590565b6002545b90565b3360009081526003602052604090206001015460ff1680156104e55750600160a060020a03811660009081526003602052604090206001015460ff16155b806104f05750600254155b15156104fb57600080fd5b600160a060020a0381161580159061051557506000600254115b806105325750600160a060020a0381161580156105325750600254155b151561053d57600080fd5b600160a060020a0381161561055e5761055e81674563918244f400006108e9565b61057033674563918244f400006108e9565b600160a060020a031660009081526003602052604090206001908101805460ff19169091179055565b600160a060020a03831660009081526001602090815260408083203384529091528120546105cd908363ffffffff61095d16565b600160a060020a03851660009081526001602090815260408083203384529091529020556105fc8484846109fc565b5060019392505050565b601281565b6000600160a060020a038316151561062257600080fd5b336000908152600160209081526040808320600160a060020a0387168452909152902054610656908363ffffffff610b6216565b336000908152600160208181526040808420600160a060020a03989098168452969052949020555090919050565b600160a060020a03811660009081526003602090815260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156107235780601f106106f857610100808354040283529160200191610723565b820191906000526020600020905b81548152906001019060200180831161070657829003601f168201915b50505050509050919050565b600160a060020a031660009081526020819052604090205490565b805160001061075857600080fd5b336000908152600360209081526040909120825161077892840190610c88565b5050565b60408051808201909152600281527f4d43000000000000000000000000000000000000000000000000000000000000602082015281565b6000600160a060020a03831615156107ca57600080fd5b336000908152600160209081526040808320600160a060020a0387168452909152902054610656908363ffffffff61095d16565b600061080b3384846109fc565b50600192915050565b60036020908152600091825260409182902080548351601f600260001961010060018616150201909316929092049182018490048402810184019094528084529092918391908301828280156108ab5780601f10610880576101008083540402835291602001916108ab565b820191906000526020600020905b81548152906001019060200180831161088e57829003601f168201915b5050506001909301549192505060ff1682565b600160a060020a03918216600090815260016020908152604080832093909416825291909152205490565b600160a060020a03821615156108fe57600080fd5b600254610911908263ffffffff610b6216565b600255600160a060020a03821660009081526020819052604090205461093d908263ffffffff610b6216565b600160a060020a0390921660009081526020819052604090209190915550565b600080838311156109f557604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152603560248201527f43616e27742073756274726163742061206e756d6265722066726f6d2061207360448201527f6d616c6c6572206f6e6520776974682075696e74730000000000000000000000606482015290519081900360840190fd5b5050900390565b6000600160a060020a0383161515610a1357600080fd5b600160a060020a038416600090815260208190526040902054610a3c908363ffffffff61095d16565b600160a060020a038086166000908152602081905260408082209390935590851681522054610a71908363ffffffff610b6216565b600160a060020a0380851660008181526020818152604080832095909555928816815260038352838120918152600290910190915220546005118015610ac9575082600160a060020a031684600160a060020a031614155b15610b5c57600160a060020a0380851660009081526003602090815260408083209387168352600290930190522054610b0990600163ffffffff610b6216565b600160a060020a03858116600090815260036020908152604080832093881683526002909301905220819055610b5090610b4484600a610c03565b9063ffffffff610c0316565b9050610b5c84826108e9565b50505050565b600082820183811015610bfc57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152602a60248201527f526573756c742068617320746f20626520626967676572207468616e20626f7460448201527f682073756d6d616e647300000000000000000000000000000000000000000000606482015290519081900360840190fd5b9392505050565b600080808311610c7457604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601460248201527f43616e277420646976696465206279207a65726f000000000000000000000000604482015290519081900360640190fd5b8284811515610c7f57fe5b04949350505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610cc957805160ff1916838001178555610cf6565b82800160010185558215610cf6579182015b82811115610cf6578251825591602001919060010190610cdb565b50610d02929150610d06565b5090565b6104a491905b80821115610d025760008155600101610d0c5600a165627a7a723058204205333107b6a6557564d0eeb44a519ec257946e159a3c2950bbff78cf5c9e370029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_REFER = "refer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_GETNAME = "getName";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_UPDATENAME = "updateName";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_USERDATA = "userData";

    public static final String FUNC_ALLOWANCE = "allowance";

    @Deprecated
    protected ERC20(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC20(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC20(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC20(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Utf8String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> approve(Address spender, Uint256 value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(spender, value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> refer(Address _referred) {
        final Function function = new Function(
                FUNC_REFER, 
                Arrays.<Type>asList(_referred), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferFrom(Address from, Address to, Uint256 value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(from, to, value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint8> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> increaseAllowance(Address spender, Uint256 addedValue) {
        final Function function = new Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(spender, addedValue), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Utf8String> getName(Address _address) {
        final Function function = new Function(FUNC_GETNAME, 
                Arrays.<Type>asList(_address), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> balanceOf(Address owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(owner), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> updateName(Utf8String _name) {
        final Function function = new Function(
                FUNC_UPDATENAME, 
                Arrays.<Type>asList(_name), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Utf8String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> decreaseAllowance(Address spender, Uint256 subtractedValue) {
        final Function function = new Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(spender, subtractedValue), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer(Address _to, Uint256 _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(_to, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<Utf8String, Bool>> userData(Address param0) {
        final Function function = new Function(FUNC_USERDATA, 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple2<Utf8String, Bool>>(
                new Callable<Tuple2<Utf8String, Bool>>() {
                    @Override
                    public Tuple2<Utf8String, Bool> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<Utf8String, Bool>(
                                (Utf8String) results.get(0), 
                                (Bool) results.get(1));
                    }
                });
    }

    public RemoteCall<Uint256> allowance(Address owner, Address spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(owner, spender), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public static RemoteCall<ERC20> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC20.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ERC20> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC20.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ERC20> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC20.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ERC20> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC20.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static ERC20 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC20(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC20 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC20(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC20 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC20(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC20 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC20(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
