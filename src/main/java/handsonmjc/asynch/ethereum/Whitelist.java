package handsonmjc.asynch.ethereum;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code. <p><strong>Do not modify!</strong> <p>Please use the <a
 * href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or the
 * org.web3j.codegen.SolidityFunctionWrapperGenerator in the <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen
 * module</a> to update.
 *
 * <p>Generated with web3j version 3.1.0.
 */
public class Whitelist extends Contract {

  private static final String BINARY = null;

  protected static final HashMap<String, String> _addresses;

  static {
    _addresses = new HashMap<>();
  }

  private Whitelist(String contractAddress, Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  private Whitelist(String contractAddress, Web3j web3j, TransactionManager transactionManager,
      BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  public RemoteCall<TransactionReceipt> setOwner(String _owner) {
    Function function = new Function(
        "setOwner",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<Boolean> isOwner(String _wallet) {
    Function function = new Function("isOwner",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_wallet)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
        }));
    return executeRemoteCallSingleValueReturn(function, Boolean.class);
  }

  public RemoteCall<Boolean> isWhitelisted(String _wallet) {
    Function function = new Function("isWhitelisted",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_wallet)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
        }));
    return executeRemoteCallSingleValueReturn(function, Boolean.class);
  }

  public RemoteCall<TransactionReceipt> updateWallet(String _wallet, String _data) {
    Function function = new Function(
        "updateWallet",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_wallet),
            new org.web3j.abi.datatypes.Utf8String(_data)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<BigInteger> ownersLimit() {
    Function function = new Function("ownersLimit",
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<BigInteger> whitelistLength() {
    Function function = new Function("whitelistLength",
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<Tuple2<String, Boolean>> whitelist(String param0) {
    final Function function = new Function("whitelist",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
        }, new TypeReference<Bool>() {
        }));
    return new RemoteCall<Tuple2<String, Boolean>>(
        new Callable<Tuple2<String, Boolean>>() {
          @Override
          public Tuple2<String, Boolean> call() throws Exception {
            List<Type> results = executeCallMultipleValueReturn(function);
            ;
            return new Tuple2<String, Boolean>(
                (String) results.get(0).getValue(),
                (Boolean) results.get(1).getValue());
          }
        });
  }

  public RemoteCall<TransactionReceipt> unsetOwner(String _owner) {
    Function function = new Function(
        "unsetOwner",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<TransactionReceipt> removeWallet(String _wallet) {
    Function function = new Function(
        "removeWallet",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_wallet)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<BigInteger> ownersCount() {
    Function function = new Function("ownersCount",
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public RemoteCall<TransactionReceipt> addWallet(String _wallet, String _data) {
    Function function = new Function(
        "addWallet",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_wallet),
            new org.web3j.abi.datatypes.Utf8String(_data)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<String> walletData(String _wallet) {
    Function function = new Function("walletData",
        Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_wallet)),
        Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
        }));
    return executeRemoteCallSingleValueReturn(function, String.class);
  }

  public static RemoteCall<Whitelist> deploy(Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit, BigInteger _limit, String _owner) {
    String encodedConstructor = FunctionEncoder.encodeConstructor(
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_limit),
            new org.web3j.abi.datatypes.Address(_owner)));
    return deployRemoteCall(Whitelist.class, web3j, credentials, gasPrice, gasLimit, BINARY,
        encodedConstructor);
  }

  public static RemoteCall<Whitelist> deploy(Web3j web3j, TransactionManager transactionManager,
      BigInteger gasPrice, BigInteger gasLimit, BigInteger _limit, String _owner) {
    String encodedConstructor = FunctionEncoder.encodeConstructor(
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_limit),
            new org.web3j.abi.datatypes.Address(_owner)));
    return deployRemoteCall(Whitelist.class, web3j, transactionManager, gasPrice, gasLimit, BINARY,
        encodedConstructor);
  }

  public static Whitelist load(String contractAddress, Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    return new Whitelist(contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  public static Whitelist load(String contractAddress, Web3j web3j,
      TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    return new Whitelist(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  protected String getStaticDeployedAddress(String networkId) {
    return _addresses.get(networkId);
  }
}
