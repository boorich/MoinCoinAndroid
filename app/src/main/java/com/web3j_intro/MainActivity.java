package com.web3j_intro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;
import java.security.Provider;
import java.security.Security;

public class MainActivity extends AppCompatActivity {

    private Web3j web3;
    //FIXME: Add your own password here
    private final String password = "medium";
    private String walletPath;
    private File walletDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBouncyCastle();
        setContentView(R.layout.activity_main);
        walletPath = getFilesDir().getAbsolutePath();
        android.util.Log.d("[moin] walletPath", walletPath);
        walletDir = new File(walletPath);
        android.util.Log.d("[moin] walletDir Read", String.valueOf(walletDir.canRead()));
        android.util.Log.d("[moin] walletDir Write", String.valueOf(walletDir.canWrite()));

    }

    private void setupBouncyCastle() {
        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider == null) {
            // Web3j will set up the provider lazily when it's first used.
            return;
        }
        if (provider.getClass().equals(BouncyCastleProvider.class)) {
            // BC with same package name, shouldn't happen in real life.
            return;
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        android.util.Log.d("[moin] Security", "Remove provider skipped");
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    public void connectToEthNetwork(View v) {
        toastAsync("Connecting to Ethereum network...");
        // FIXME: Add your own API key here
        web3 = Web3j.build(new HttpService("http://moin.pseudorandom.xyz:9545"));
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            EthBlockNumber ethBlock = web3.ethBlockNumber().sendAsync().get();
            int blockInt = Integer.parseInt(ethBlock.getResult().substring(2), 16);
            if(!clientVersion.hasError()) {
                toastAsync("Connected, current blocknumber is " + Integer.toString(blockInt));
            } else {
                toastAsync(clientVersion.getError().getMessage());
            }
        } catch (Exception e) {
            toastAsync(e.getMessage());
        }
    }

    public void createWallet(View v){
        try{
            android.util.Log.d("Wallet Creation", "Try Creating a wallet!");
            String filename = WalletUtils.generateNewWalletFile(password, walletDir);
            walletPath += "/" + filename;
            toastAsync("[moin] Wallet " + walletPath + " generated");


        }
        catch (Exception e){
            toastAsync(e.getMessage());
        }
    }


    public void getAddress(View v){
        try {
            toastAsync("Accessing credentials");
            Credentials credentials = WalletUtils.loadCredentials(password, walletPath);
            toastAsync("Your address is " + credentials.getAddress());
            android.util.Log.d("[moin] Address is", credentials.getAddress());
        }
        catch (Exception e){
            toastAsync(e.getMessage());
        }
    }

    public void sendTransaction(View v){
        try{
            Credentials credentials = WalletUtils.loadCredentials(password, walletPath);
            TransactionReceipt receipt = Transfer.sendFunds(web3,credentials,"0xeb2D9aAfD2b3d74D288c022Ab5b58396A4a6c677",new BigDecimal(1),Convert.Unit.ETHER).sendAsync().get();
            toastAsync("Transaction complete: " +receipt.getTransactionHash());
        }
        catch (Exception e){
            android.util.Log.d("[moin] ERROR TX", e.getMessage());
            toastAsync(e.getMessage());
        }
    }


    public void toastAsync(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}
