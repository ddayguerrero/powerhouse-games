package org.soen387.business;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.soen387.domain.CartItem;
import org.soen387.domain.Game;

public class ShoppingCart {
	
//	private static ShoppingCart instance = null;
//	
//	/**
//	 * Singleton instance of a shopping cart
//	 * @return
//	 */
//	public static ShoppingCart getInstance () {
//		if (instance == null) {
//			instance = new ShoppingCart();
//		}
//		return instance;
//	}
//	
	public ShoppingCart() {
		super();
		items = new ArrayList<CartItem>();
		subTotal = new BigDecimal(0);
		calculatedTaxes = new BigDecimal(0);
		cartSize = 0;
		isCartEmpty = true;
	}
	
	private ArrayList<CartItem> items;

	private int cartSize;
	private int currentQuantity;
	private BigDecimal subTotal;
	private BigDecimal calculatedTaxes;
	private BigDecimal grandTotal;
	private boolean isCartEmpty;
	
	public ArrayList<CartItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}

	public int getCartSize() {
		return cartSize;
	}
	public void setCartSize(int cartSize) {
		this.cartSize = cartSize;
	}
	public int getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public BigDecimal getSubTotal() {
		BigDecimal amount = BigDecimal.ZERO;
		for (CartItem<?> i : items) {
			amount = amount.add(i.getTotal());
		}
		setSubTotal(amount);
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getCalculatedTaxes() {
		return calculatedTaxes;
	}
	public void setCalculatedTaxes(BigDecimal calculatedTaxes) {
		this.calculatedTaxes = calculatedTaxes;
	}
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	public boolean isCartEmpty() {
		return isCartEmpty;
	}
	public void setCartEmpty(boolean isCartEmpty) {
		this.isCartEmpty = isCartEmpty;
	}
	
	public void updateQuantity(int id, int action) {
		for(CartItem<?> i : items) {
			Game cartGame = (Game)i.getItem();
			if(cartGame.getGameid() == id) {
				if(action == 0) {
					if(i.getQuantity() == 1) {
						removeFromCart(id);
					} else {
						i.setQuantity(i.getQuantity() - 1);
						cartSize -= 1;
					}
				} else if(action == 1) {
					i.setQuantity(i.getQuantity() + 1);
					cartSize += 1;
				}
				break;
			}
		}
		System.out.println("Quantity of item" + id + " updated!");
	}
	
	public void addToCart(CartItem cartItem) {
		if(hasUpdatedQuantity(cartItem)) {
			
		} else {
			items.add(cartItem);
			if (isCartEmpty) {
				isCartEmpty = false;
			}
		}
		cartSize += 1;
		System.out.println("Item added! Current cart size: " + cartSize);
	}
	
	public void removeFromCart(int id) {
		CartItem<?> itemToRemove = null;
		for(CartItem<?> i : items) {
			Game cartGame = (Game)i.getItem();
			if(cartGame.getGameid() == id) {
				itemToRemove = i;
				int quantity = i.getQuantity();
				cartSize = cartSize - quantity;
			}
		}
		items.remove(itemToRemove);
		System.out.println("Item removed! Current cart size: " + cartSize);
	}
	
	/**
	 * Update quantity is item exists in cart
	 * @param cartItem
	 * @return
	 */
	private boolean hasUpdatedQuantity(CartItem cartItem) {
		Game cartGameToAdd = (Game)cartItem.getItem();
		for(CartItem<?> i : items) {
			Game cartGame = (Game)i.getItem();
			if(cartGame.getGameid() == cartGameToAdd.getGameid()) {
				int newQuantity = i.getQuantity() + 1;
				i.setQuantity(newQuantity);
				return true;
			}
		}
		return false;
	}
	
	public void emptyCart() {
		this.setItems(new ArrayList<CartItem>());
		items = new ArrayList<CartItem>();
		subTotal = new BigDecimal(0);
		calculatedTaxes = new BigDecimal(0);
		cartSize = 0;
		isCartEmpty = true;
	}
}
