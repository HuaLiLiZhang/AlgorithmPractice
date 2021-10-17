package leetcode.competition;

/**
 * @author mizhu
 * @date 2021/10/17 11:00
 */
class _5903SimpleBank {
    private long[] balance;

    public _5903SimpleBank(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if(!isValidAccount(account1) || !isValidAccount(account2)){
            return false;
        }
        if (balance[account1 - 1] >= money) {
            balance[account2 - 1] += money;
            balance[account1 - 1] -= money;
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(int account, long money) {
        if(!isValidAccount(account)){
            return false;
        }
        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if(!isValidAccount(account)){
            return false;
        }
        if (balance[account - 1] >= money) {
            balance[account - 1] -= money;
            return true;
        }
        return false;
    }

    public boolean isValidAccount(int account) {
        if (account <= balance.length) {
            return true;
        }
        return false;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */
