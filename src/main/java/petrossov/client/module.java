package petrossov.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import petrossov.shared.OrderResp;

import java.util.List;
import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 777
 */
public class module implements EntryPoint {


  private CellTable<OrderResp> table = new CellTable<>();
  private Logger logger = Logger.getLogger("NameOfYourLogger");private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
  private final TextBox orderName = new TextBox();
  private final TextBox clientName = new TextBox();
  private final TextBox date = new TextBox();
  private final TextBox address = new TextBox();
  private final TextBox phoneNumber = new TextBox();
  private final TextBox price = new TextBox();
  private final TextBox prepay = new TextBox();
  private Integer id = -1;




  private ListDataProvider<OrderResp> createTable(CellTable<OrderResp> table){

      TextColumn<OrderResp> orderNameColumn = new TextColumn<OrderResp>() {
          @Override
          public String getValue(OrderResp orderResp) {
              return orderResp.getOrderName();
          }
      };
      TextColumn<OrderResp> clientNameColumn = new TextColumn<OrderResp>() {
          @Override
          public String getValue(OrderResp orderResp) {
              return orderResp.getClientName();
          }
      };
      TextColumn<OrderResp> dateColumn = new TextColumn<OrderResp>() {
          @Override
          public String getValue(OrderResp orderResp) {
              return orderResp.getDate();
          }
      };
      TextColumn<OrderResp> addressColumn = new TextColumn<OrderResp>() {
          @Override
          public String getValue(OrderResp orderResp) {
              return orderResp.getAddress();
          }
      };
      TextColumn<OrderResp> phoneNumberColumn = new TextColumn<OrderResp>() {
          @Override
          public String getValue(OrderResp orderResp) {
              return orderResp.getPhoneNumber();
          }
      };
      TextColumn<OrderResp> priceColumn = new TextColumn<OrderResp>() {
          @Override
          public String getValue(OrderResp orderResp) {
              return orderResp.getPrice();
          }
      };
      TextColumn<OrderResp> prepayColumn = new TextColumn<OrderResp>() {
          @Override
          public String getValue(OrderResp orderResp) {
              return orderResp.getPrepay();
          }
      };

    table.addColumn(orderNameColumn, "Order Name");
    table.addColumn(clientNameColumn, "Client Name");
    table.addColumn(dateColumn,"Date");
    table.addColumn(addressColumn,"Address");
    table.addColumn(phoneNumberColumn,"Phone Number");
    table.addColumn(priceColumn,"Price");
    table.addColumn(prepayColumn,"Prepay");

    ListDataProvider<OrderResp> dataProvider = new ListDataProvider<>();
    dataProvider.addDataDisplay(table);

    this.greetingService.list(new AsyncCallback<List<OrderResp>>() {
        @Override
        public void onFailure(Throwable throwable) {
            GWT.log("error while writing table",throwable);
        }

        @Override
        public void onSuccess(List<OrderResp> orders) {
            logger.info("orders: " + orders);

            dataProvider.getList().addAll(orders);
        }
    });
    return dataProvider;
  }


  public void onModuleLoad() {

      ListDataProvider<OrderResp> dataProvider = createTable(table);
      DialogBox dialog = editDialog(dataProvider);

      Button add = new Button("Add", new ClickHandler() {
          @Override
          public void onClick(ClickEvent clickEvent) {
              orderName.setValue("");
              clientName.setValue("");
              date.setValue("");
              address.setValue("");
              phoneNumber.setValue("");
              price.setValue("");
              prepay.setValue("");
              id = -1;
              dialog.center();
              dialog.show();
          }
      });

      Button edit = new Button("Edit", (ClickHandler) clickEvent -> {

          int rowIndex = table.getKeyboardSelectedRow();
          logger.info("rowIndex: " + rowIndex);
          OrderResp orderResp = dataProvider.getList().get(rowIndex);
          orderName.setValue(orderResp.getOrderName());
          clientName.setValue(orderResp.getClientName());
          date.setValue(orderResp.getDate());
          address.setValue(orderResp.getAddress());
          phoneNumber.setValue(orderResp.getPhoneNumber());
          price.setValue(orderResp.getPrice());
          prepay.setValue(orderResp.getPrepay());
          id = orderResp.getId();

          logger.info("id: " + id);

          dialog.show();
          dialog.center();

      });

      Button delete = new Button("Delete", new ClickHandler() {
          @Override
          public void onClick(ClickEvent clickEvent) {
              final int index = table.getKeyboardSelectedRow();
              OrderResp order = dataProvider.getList().get(index);
              greetingService.delete(order, new AsyncCallback<Boolean>() {
                  @Override
                  public void onFailure(Throwable throwable) {
                      GWT.log("Delete error",throwable);
                  }

                  @Override
                  public void onSuccess(Boolean aBoolean) {
                    dataProvider.getList().remove(index);
                  }
              });
          }
      });

      HorizontalPanel control = new HorizontalPanel();
      control.add(add);
      control.add(edit);
      control.add(delete);
      VerticalPanel panel = new VerticalPanel();
      panel.add(control);
      panel.add(table);
      RootPanel.get().add(panel);
  }

    private DialogBox editDialog(ListDataProvider<OrderResp> dataProvider) {
      final DialogBox dialogBox = new DialogBox();
      dialogBox.setText("Add note");
      dialogBox.setAnimationEnabled(true);
      VerticalPanel dPanel = new VerticalPanel();
      HorizontalPanel orderNamePanel = new HorizontalPanel();
      Label label = new Label("Order Name");
      label.setWidth("100px");
      orderNamePanel.add(label);
      orderNamePanel.add(orderName);
      dPanel.add(orderNamePanel);

      HorizontalPanel clientNamePanel = new HorizontalPanel();
      label = new Label("Client Name");
      label.setWidth("100px");
      clientNamePanel.add(label);
      clientNamePanel.add(clientName);
      dPanel.add(clientNamePanel);

        HorizontalPanel datePanel = new HorizontalPanel();
        label = new Label("Date");
        label.setWidth("100px");
        datePanel.add(label);
        datePanel.add(date);
        dPanel.add(datePanel);

        HorizontalPanel addressPanel = new HorizontalPanel();
        label = new Label("Address");
        label.setWidth("100px");
        addressPanel.add(label);
        addressPanel.add(address);
        dPanel.add(addressPanel);

        HorizontalPanel phoneNumberPanel = new HorizontalPanel();
        label = new Label("Phone Number");
        label.setWidth("100px");
        phoneNumberPanel.add(label);
        phoneNumberPanel.add(phoneNumber);
        dPanel.add(phoneNumberPanel);

        HorizontalPanel pricePanel = new HorizontalPanel();
        label = new Label("Price");
        label.setWidth("100px");
        pricePanel.add(label);
        pricePanel.add(price);
        dPanel.add(pricePanel);

        HorizontalPanel prepayPanel = new HorizontalPanel();
        label = new Label("Prepay");
        label.setWidth("100px");
        prepayPanel.add(label);
        prepayPanel.add(prepay);
        dPanel.add(prepayPanel);

      HorizontalPanel dControl = new HorizontalPanel();

      dControl.add(new Button("Save", (ClickHandler) clickEvent -> {
          int rowIndex = table.getKeyboardSelectedRow();
          logger.info("rowIndex in save button: " + rowIndex);
          logger.info("Save button Id: " + id);
          greetingService.save(new OrderResp(id, orderName.getValue(),
                          clientName.getValue(),
                          date.getValue(),address.getValue(),phoneNumber.getValue(),
                          price.getValue(),prepay.getValue()),
                  new AsyncCallback<OrderResp>() {
                      @Override
                      public void onFailure(Throwable throwable) {
                          GWT.log("save error" + throwable,throwable);
                      }
                      @Override
                      public void onSuccess(OrderResp order) {
                          logger.info("order id: " + order.getId());
                          logger.info("field id: " + id);
                              if (id == -1 ) {
                                  //add element
                                  dataProvider.getList().add(order);
                                  logger.info("order.id before" + order.toString());
                                  logger.info("DATAPROVIDER LIST: ");
                                  dataProvider.getList().forEach(i -> logger.info(i.toString()));
                              }else {
                                  //edit element
                                  logger.info("DATAPROVIDER LIST: ");
                                  dataProvider.getList().forEach(i -> logger.info(i.toString()));

                                  dataProvider.getList()
                                          .add(rowIndex,order);
                                  dataProvider.getList().remove(rowIndex+1);
                              }
                          logger.info("order.id after" + order.getId());
                          dialogBox.hide();
                      }
                  });

      }
                   ));

      dControl.add(new Button("Cancel", (ClickHandler) clickEvent -> dialogBox.hide()));
      dPanel.add(dControl);
      dialogBox.setWidget(dPanel);
        return dialogBox;
    }
}
